package dto;

public class MusicanDTO {
public String musicianid;
public String musicianpw;
public String musicianname;
public int musicianage;
public String musicianphone;
public String musicianaddr;
public MusicanDTO(String musicianid, String musicianpw, String musicianname, int musicianage, String musicianphone,
		String musicianaddr) {
	this.musicianid = musicianid;
	this.musicianpw = musicianpw;
	this.musicianname = musicianname;
	this.musicianage = musicianage;
	this.musicianphone = musicianphone;
	this.musicianaddr = musicianaddr;
}

}
