package osonsot.util;

import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.springframework.stereotype.Component;
import osonsot.dto.ImagePath;
import osonsot.dto.TelegraphResult;
import osonsot.dto.Translation;
import osonsot.entity.poster.Image;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class Utils {

    private static final HttpClient client =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    private static final Locale locale = Locale.US;
    public static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    public static Boolean isNumeric(String number) {
        try {
            Long.parseLong(number);
            Integer.parseInt(number);
            Double.parseDouble(number);
            Float.parseFloat(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String createTelegraphPoster(Poster poster) {
        Map<Object, Object> params = new HashMap<>();
        params.put("access_token", System.getenv("TELEGRAPH_ACCESS_TOKEN"));
        params.put("return_content", "false");
        params.put("title", poster.getTitle());
        params.put("author_name", poster.getOwner().getContact());
        params.put("content", prepareContent(poster));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormData(params))
                .uri(URI.create("https://api.telegra.ph/createPage"))
                .setHeader("User-Agent", "osonsot_bot")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        TelegraphResult result = new GsonBuilder().
                create().fromJson(response == null ? "{}" : response.body(), TelegraphResult.class);
        return result.getResult().getUrl();
    }

    public static String editTelegraphPoster(Poster poster) {
        Map<Object, Object> params = new HashMap<>();
        params.put("access_token", System.getenv("TELEGRAPH_ACCESS_TOKEN"));
        params.put("return_content", "false");
        params.put("title", poster.getTitle());
        params.put("author_name", poster.getOwner().getContact());
        params.put("content", prepareContent(poster));
        HttpRequest request = HttpRequest.newBuilder()
                .POST(buildFormData(params))
                .uri(URI.create("https://api.telegra.ph/editPage/" + poster.getTelegraphUrl().replaceFirst("https://telegra.ph/", "")))
                .setHeader("User-Agent", "osonsot_bot")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        TelegraphResult result = new GsonBuilder().
                create().fromJson(response == null ? "{}" : response.body(), TelegraphResult.class);
        return result.getResult().getUrl();
    }

    private static String prepareContent(Poster poster) {
        StringBuilder contentBody = new StringBuilder();
        contentBody.append("[{\"tag\":\"pre\",\"children\":[\"%s\"]},".formatted(getPosterInfoWithoutTelegraphUrl(poster, Language.RUSSIAN)));
        for (Image image : poster.getImages()) {
            contentBody.append("{\"tag\":\"img\",\"attrs\":{\"src\":\"%s\"}},".formatted(image.getPath()));
        }
        contentBody.append("]");
        contentBody.deleteCharAt(contentBody.length() - 2);
        return contentBody.toString();
    }

    public static String uploadTelegraph(File file) {
        try {
            HttpEntity entity = MultipartEntityBuilder.create()
                    .addPart("files", new FileBody(file))
                    .setContentType(ContentType.MULTIPART_FORM_DATA)
                    .build();
            Pipe pipe = Pipe.open();
            new Thread(() -> {
                try (OutputStream outputStream = Channels.newOutputStream(pipe.sink())) {
                    // Write the encoded data to the pipeline.
                    entity.writeTo(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
            HttpRequest request = HttpRequest.newBuilder(new URI("https://telegra.ph/upload"))
                    .header("Content-Type", entity.getContentType().getValue())
                    .POST(HttpRequest.BodyPublishers.ofInputStream(() -> Channels.newInputStream(pipe.source()))).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            ImagePath[] path =
                    new GsonBuilder().create().fromJson(response.body(), ImagePath[].class);
            Files.deleteIfExists(file.toPath());
            return "https://telegra.ph/" + path[0].getSrc();
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTranslation(String word, Language lang) {
        Map<Object, Object> data = new HashMap<>();
        data.put("key", System.getenv("TRANSLATOR_API_KEY"));
        data.put("text", word);
        data.put("lang", "uz-" + lang.getLangCode());
        // data.put("options", 1);
        HttpRequest request =
                HttpRequest.newBuilder()
                        .POST(buildFormData(data))
                        .uri(URI.create("https://translate.yandex.net/api/v1.5/tr.json/translate"))
                        .setHeader("User-Agent", "osonsot_bot")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Translation translation =
                new GsonBuilder()
                        .create()
                        .fromJson(response != null ? response.body() : "{\"text\":\"\"}", Translation.class);
        return translation.translation();
    }

    private static HttpRequest.BodyPublisher buildFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    public static String getPosterInfo(Poster poster, Language language) {
        return Words.POSTER_INFO
                .lang(language)
                .formatted(
                        poster.getTitle(),
                        currencyFormatter.format(poster.getPrice()),
                        poster.getDescription(),
                        poster.getLocation().getRegion().getRegion() + ", " + poster.getLocation().getName(),
                        poster.getSold() ? Words.SOLD.lang(language) : Words.ACTIVE.lang(language),
                        poster.getTelegraphUrl(),
                        poster.getAddedDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")),
                        poster.getOwner().getUsername() != null
                                ? poster.getOwner().getContact() + "\n@" + poster.getOwner().getUsername()
                                : poster.getOwner().getContact());
    }

    public static String getPosterInfoForOwner(Poster poster, Language language) {
        return Words.POSTER_INFO_FOR_OWNER
                .lang(language)
                .formatted(
                        poster.getTitle(),
                        currencyFormatter.format(poster.getPrice()),
                        poster.getDescription(),
                        poster.getOwner().getUsername() != null
                                ? poster.getOwner().getContact() + "\n@" + poster.getOwner().getUsername()
                                : poster.getOwner().getContact(),
                        poster.getTelegraphUrl(),
                        poster.getAccepted() ? Words.ACCEPTED.lang(language) : Words.WAITING.lang(language),
                        poster.getCategory().getName(language),
                        poster.getAddedDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")));
    }

    public static String getPosterInfoWithoutTelegraphUrl(Poster poster, Language language) {
        return Words.POSTER_INFO_WITHOUT_URL
                .lang(language)
                .formatted(
                        poster.getTitle(),
                        currencyFormatter.format(poster.getPrice()),
                        poster.getDescription(),
                        poster.getOwner().getUsername() != null
                                ? poster.getOwner().getContact() + "\n@" + poster.getOwner().getUsername()
                                : poster.getOwner().getContact(),
                        poster.getAccepted() ? Words.ACCEPTED.lang(language) : Words.WAITING.lang(language),
                        poster.getCategory().getName(language),
                        poster.getAddedDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")));
    }

    public static boolean validPhone(String phone) {
        try {
            Long.parseLong(phone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validUzbPhone(String phone) {
        if (phone.startsWith("+")) phone = phone.substring(1);
        return phone.length() == 12 && phone.startsWith("998");
    }

    public static boolean invalidCategoryName(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isWhitespace(c) && !(c == '\'' || c == '`'))
                return true;
        }
        return false;
    }
}
