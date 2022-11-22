package osonsot.base.handler;

import osonsot.base.command.CallbackCommand;
import osonsot.base.command.TextCommand;
import osonsot.mainbot.command.Default;
import osonsot.mainbot.command.inline.Back;
import osonsot.mainbot.command.inline.Close;
import osonsot.mainbot.command.inline.Home;
import osonsot.mainbot.command.inline.SubCategories;
import osonsot.mainbot.command.inline.admin.*;
import osonsot.mainbot.command.inline.lang.ChangeLang;
import osonsot.mainbot.command.inline.lang.SelectLang;
import osonsot.mainbot.command.inline.location.ChangeLocation;
import osonsot.mainbot.command.inline.location.District;
import osonsot.mainbot.command.inline.location.Region;
import osonsot.mainbot.command.inline.user.poster.*;
import osonsot.mainbot.command.inline.user.setting.*;
import osonsot.mainbot.command.slash.Done;
import osonsot.mainbot.command.slash.Start;
import osonsot.mainbot.command.text.Cancel;
import osonsot.mainbot.handler.processor.ProcessContact;
import osonsot.mainbot.handler.processor.ProcessPhoto;
import osonsot.service.BotService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseHandler {
    protected final List<TextCommand> textCommands;
    protected final List<CallbackCommand> callbackCommands;
    protected final Map<String, Processor> processors;

    public BaseHandler(BotService service) {
        processors = new HashMap<>();
        callbackCommands = new ArrayList<>();
        textCommands = new ArrayList<>();
        // add text commands
        textCommands.add(new Start(service));
        textCommands.add(new Done(service));
        textCommands.add(new Cancel(service));
        textCommands.add(new Default(service));
        // add callback commands
        callbackCommands.add(new SelectLang(service));
        callbackCommands.add(new ChangeLang(service));
        callbackCommands.add(new Region(service));
        callbackCommands.add(new District(service));
        callbackCommands.add(new Settings(service));
        callbackCommands.add(new ChangeLocation(service));
        callbackCommands.add(new DeleteContact(service));
        callbackCommands.add(new AddContact(service));
        callbackCommands.add(new EditContact(service));
        callbackCommands.add(new EditContactPanel(service));
        callbackCommands.add(new EditCategory(service));
        callbackCommands.add(new Categories(service));
        callbackCommands.add(new AddCategory(service));
        callbackCommands.add(new DeleteCategory(service));
        callbackCommands.add(new SubCategories(service));
        callbackCommands.add(new Recents(service));
        callbackCommands.add(new RejectConfirmRecentPoster(service));
        callbackCommands.add(new SendRejectionMessage(service));
        callbackCommands.add(new SeeConfirmedPoster(service));
        callbackCommands.add(new ControlNotification(service));
        callbackCommands.add(new CreatePoster(service));
        callbackCommands.add(new EditPoster(service));
        callbackCommands.add(new PlacePoster(service));
        callbackCommands.add(new RemovePoster(service));
        callbackCommands.add(new MyPosters(service));
        callbackCommands.add(new UserInfo(service));
        callbackCommands.add(new Home(service));
        callbackCommands.add(new Close(service));
        callbackCommands.add(new Back(service));
        // add processors
        processors.put("contact", new ProcessContact(service));
        processors.put("photo", new ProcessPhoto(service));
    }
}
