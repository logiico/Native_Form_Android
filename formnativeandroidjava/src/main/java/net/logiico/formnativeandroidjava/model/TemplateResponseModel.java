package net.logiico.formnativeandroidjava.model;

public class TemplateResponseModel {
    public int Id;
    public String Name;
    public String Link;
    public int Version;


    public static RoomTemplate convertToRoom(TemplateResponseModel responseModel) {

        RoomTemplate roomTemplate = new RoomTemplate();

        roomTemplate.id = responseModel.Id;
        roomTemplate.link = responseModel.Link;
        roomTemplate.name = responseModel.Name;
        roomTemplate.version = responseModel.Version;

        return roomTemplate;
    }
}
