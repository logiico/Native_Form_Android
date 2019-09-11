package net.logiico.formnativeandroidjava.model;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.executor.DBThreadExecutor;

import java.util.List;

import javax.inject.Inject;

public class TemplateResultRepository {

    @Inject
    public TemplateResultDao templateResultDao;

    @Inject
    public TemplateResultRepository() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }

    public void insert(RoomTemplateResult roomTemplateResult) {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                templateResultDao.insertTransaction(roomTemplateResult);
            }
        });
    }

    public List<RoomTemplateResult> getByMissionId(int missionId) {
        return templateResultDao.getByMissionIdTransaction(missionId);
    }

    public List<RoomTemplateResult> getAllInspection() {
        return templateResultDao.getAllInspection();
    }

    public List<RoomTemplateResult> getInspectionListByStatus(int status) {
        List<RoomTemplateResult> templateResultList;

        if (status == RoomTemplateResult.EnumResultStatusInServer.NO_STATUS.getIntValue()) {
            templateResultList = templateResultDao.getAllInspection();

        } else {
            templateResultList = templateResultDao.getAllByServerStatus(status);
        }

        return templateResultList;
    }

    public List<RoomTemplateResult> getAllMissionByAppStatus(int status) {
        return templateResultDao.getAllMissionByAppStatus(status);
    }

    public boolean update(RoomTemplateResult templateResult) {
        boolean wasUpdated = false;

        if (templateResultDao.update(templateResult) > 0)
            wasUpdated = true;

        return wasUpdated;
    }
}
