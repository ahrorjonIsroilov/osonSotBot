package osonsot.service;

import osonsot.repo.BaseRepo;

public class BaseService<R extends BaseRepo> {
    R repo;

    public BaseService(R repo) {
        this.repo = repo;
    }
}
