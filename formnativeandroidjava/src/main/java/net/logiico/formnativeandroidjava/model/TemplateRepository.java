package net.logiico.formnativeandroidjava.model;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.callback.BaseListCallback;
import net.logiico.formnativeandroidjava.executor.DBThreadExecutor;
import net.logiico.formnativeandroidjava.executor.MainThreadExecutor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TemplateRepository {

    @Inject
    public TemplateDao templateDao;

    @Inject
    public TemplateRepository() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }

    public void insert(RoomTemplate template) {

        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                templateDao.insert(template);
            }
        });

    }

    public void getAll(BaseListCallback<RoomTemplate> callback) {

        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                List<RoomTemplate> templateList = templateDao.getAllTemplates();

                if (callback != null) {

                    MainThreadExecutor.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.listReady(templateList);
                        }
                    });
                }

            }
        });

    }

    public String getTemplateContentModelById(int missionId) {
        return templateDao.getTemplateContentModelById(missionId);
    }

    public void updateTemplateContentModel(int templateId, String content) {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                templateDao.updateTemplateContentModel(templateId, content);
            }
        });
    }

    public void clearTable() {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                templateDao.truncate();
            }
        });
    }

    public List<TemplateResponseModel> filterRequiresUpdate(List<TemplateResponseModel> responseList) {

        List<TemplateResponseModel> requireUpdateList = new ArrayList<>();

        if (responseList != null && responseList.size() > 0) {

            for (TemplateResponseModel response : responseList) {

                RoomTemplate roomTemplate = templateDao.getTemplateById(response.Id);

                if (roomTemplate == null || roomTemplate.version != response.Version) {
                    requireUpdateList.add(response);
                }

            }
        }

        return requireUpdateList;
    }

}
