package net.logiico.formnativeandroidjava.model;

import net.logiico.formnativeandroidjava.MyApplication;
import net.logiico.formnativeandroidjava.executor.DBThreadExecutor;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

public class FileModelRepository implements Serializable {

    @Inject
    public FileModelDao fileModelDao;

    @Inject
    public FileModelRepository() {
        MyApplication.getInstance().getAppComponent().inject(this);
    }


    public void insert(RoomFileModel fileModel) {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                fileModelDao.insert(fileModel);

            }
        });
    }

    public void insertAll(List<RoomFileModel> fileModelList) {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                fileModelDao.insertAll(fileModelList);

            }
        });
    }


    public void setFileSaved(RoomFileModel fileModel) {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                fileModel.status = RoomFileModel.EnumFileStatus.SAVED.getIntValue();
                fileModelDao.insert(fileModel);
            }
        });
    }

    public void setFileListSaved(List<RoomFileModel> fileModelList) {
        for (RoomFileModel fileModel : fileModelList) {
            setFileSaved(fileModel);
        }
    }

    public void setFileSent(RoomFileModel fileModel) {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                fileModel.status = RoomFileModel.EnumFileStatus.SENT.getIntValue();
                fileModelDao.insert(fileModel);
            }
        });
    }

    public void setFileFailed(RoomFileModel fileModel) {
        DBThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                fileModel.numberOfFailedSends++;
                fileModelDao.insert(fileModel);
            }
        });
    }

    public List<RoomFileModel> getNotSentList() {
        return fileModelDao.getNotSentList();
    }

    public List<RoomFileModel> getListByUId(String uId) {
        return fileModelDao.getListByUid(uId);
    }

    public RoomFileModel getByUid_Title(String uId, String title) {
        return fileModelDao.getByUid_Title(uId, title);
    }

    public void submitErrorSending(String uid, String title) {
        RoomFileModel roomFileModel = fileModelDao.getByUid_Title(uid, title);
        fileModelDao.submitErrorSending(uid, title, roomFileModel.numberOfFailedSends + 1);
    }

}
