package id.putraprima.skorbola.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String teamHome;
    private String teamAway;
    private Uri logoHomeUri;
    private Uri logoAwayUri;

    public Data(String teamHome, String teamAway, Uri logoHomeUri, Uri logoAwayUri) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.logoHomeUri = logoHomeUri;
        this.logoAwayUri = logoAwayUri;
    }

    protected Data(Parcel in) {
        teamHome = in.readString();
        teamAway = in.readString();
        logoHomeUri = in.readParcelable(Uri.class.getClassLoader());
        logoAwayUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
    }

    public String getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(String teamAway) {
        this.teamAway = teamAway;
    }

    public Uri getLogoHomeUri() {
        return logoHomeUri;
    }

    public void setLogoHomeUri(Uri logoHomeUri) {
        this.logoHomeUri = logoHomeUri;
    }

    public Uri getLogoAwayUri() {
        return logoAwayUri;
    }

    public void setLogoAwayUri(Uri logoAwayUri) {
        this.logoAwayUri = logoAwayUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teamHome);
        dest.writeString(teamAway);
        dest.writeParcelable(logoHomeUri, flags);
        dest.writeParcelable(logoAwayUri, flags);
    }
}
