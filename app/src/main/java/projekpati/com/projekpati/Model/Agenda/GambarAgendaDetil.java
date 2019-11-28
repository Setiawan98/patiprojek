package projekpati.com.projekpati.Model.Agenda;

public class GambarAgendaDetil {

    String file_agenda_id, file_agenda_img;

    public GambarAgendaDetil(String file_agenda_id, String file_agenda_img) {
        this.file_agenda_id = file_agenda_id;
        this.file_agenda_img = file_agenda_img;
    }

    public String getFile_agenda_id() {
        return file_agenda_id;
    }

    public String getFile_agenda_img() {
        return file_agenda_img;
    }

    public void setFile_agenda_id(String file_agenda_id) {
        this.file_agenda_id = file_agenda_id;
    }

    public void setFile_agenda_img(String file_agenda_img) {
        this.file_agenda_img = file_agenda_img;
    }
}
