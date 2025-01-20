package dev.prithwish.weather_api.model;

import java.io.Serializable;
import java.util.List;

public class Day implements Serializable {
    private String datetime;
    private long datetimeEpoch;
    private double tempmax;
    private double tempmin;
    private double temp;
    private double feelslikemax;
    private double feelslikemin;
    private double feelslike;
    private double dew;
    private double humidity;
    private double precip;
    private double precipprob;
    private double precipcover;
    private List<String> preciptype;
    private double snow;
    private double snowdepth;
    private double windgust;
    private double windspeed;
    private double winddir;
    private double pressure;
    private double cloudcover;
    private double visibility;
    private double solarradiation;
    private double solarenergy;
    private double uvindex;
    private double severerisk;
    private String sunrise;
    private long sunriseEpoch;
    private String sunset;
    private long sunsetEpoch;
    private double moonphase;
    private String conditions;
    private String description;
    private String icon;

    public Day() {
    }

    public Day(String datetime, long datetimeEpoch, double tempmax, double tempmin, double temp, double feelslikemax, double feelslikemin, double feelslike, double dew, double humidity, double precip, double precipprob, double precipcover, List<String> preciptype, double snow, double snowdepth, double windgust, double windspeed, double winddir, double pressure, double cloudcover, double visibility, double solarradiation, double solarenergy, double uvindex, double severerisk, String sunrise, long sunriseEpoch, String sunset, long sunsetEpoch, double moonphase, String conditions, String description, String icon) {
        this.datetime = datetime;
        this.datetimeEpoch = datetimeEpoch;
        this.tempmax = tempmax;
        this.tempmin = tempmin;
        this.temp = temp;
        this.feelslikemax = feelslikemax;
        this.feelslikemin = feelslikemin;
        this.feelslike = feelslike;
        this.dew = dew;
        this.humidity = humidity;
        this.precip = precip;
        this.precipprob = precipprob;
        this.precipcover = precipcover;
        this.preciptype = preciptype;
        this.snow = snow;
        this.snowdepth = snowdepth;
        this.windgust = windgust;
        this.windspeed = windspeed;
        this.winddir = winddir;
        this.pressure = pressure;
        this.cloudcover = cloudcover;
        this.visibility = visibility;
        this.solarradiation = solarradiation;
        this.solarenergy = solarenergy;
        this.uvindex = uvindex;
        this.severerisk = severerisk;
        this.sunrise = sunrise;
        this.sunriseEpoch = sunriseEpoch;
        this.sunset = sunset;
        this.sunsetEpoch = sunsetEpoch;
        this.moonphase = moonphase;
        this.conditions = conditions;
        this.description = description;
        this.icon = icon;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public long getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public void setDatetimeEpoch(long datetimeEpoch) {
        this.datetimeEpoch = datetimeEpoch;
    }

    public double getTempmax() {
        return tempmax;
    }

    public void setTempmax(double tempmax) {
        this.tempmax = tempmax;
    }

    public double getTempmin() {
        return tempmin;
    }

    public void setTempmin(double tempmin) {
        this.tempmin = tempmin;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelslikemax() {
        return feelslikemax;
    }

    public void setFeelslikemax(double feelslikemax) {
        this.feelslikemax = feelslikemax;
    }

    public double getFeelslikemin() {
        return feelslikemin;
    }

    public void setFeelslikemin(double feelslikemin) {
        this.feelslikemin = feelslikemin;
    }

    public double getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(double feelslike) {
        this.feelslike = feelslike;
    }

    public double getDew() {
        return dew;
    }

    public void setDew(double dew) {
        this.dew = dew;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecip() {
        return precip;
    }

    public void setPrecip(double precip) {
        this.precip = precip;
    }

    public double getPrecipprob() {
        return precipprob;
    }

    public void setPrecipprob(double precipprob) {
        this.precipprob = precipprob;
    }

    public double getPrecipcover() {
        return precipcover;
    }

    public void setPrecipcover(double precipcover) {
        this.precipcover = precipcover;
    }

    public List<String> getPreciptype() {
        return preciptype;
    }

    public void setPreciptype(List<String> preciptype) {
        this.preciptype = preciptype;
    }

    public double getSnow() {
        return snow;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }

    public double getSnowdepth() {
        return snowdepth;
    }

    public void setSnowdepth(double snowdepth) {
        this.snowdepth = snowdepth;
    }

    public double getWindgust() {
        return windgust;
    }

    public void setWindgust(double windgust) {
        this.windgust = windgust;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getWinddir() {
        return winddir;
    }

    public void setWinddir(double winddir) {
        this.winddir = winddir;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(double cloudcover) {
        this.cloudcover = cloudcover;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public double getSolarradiation() {
        return solarradiation;
    }

    public void setSolarradiation(double solarradiation) {
        this.solarradiation = solarradiation;
    }

    public double getSolarenergy() {
        return solarenergy;
    }

    public void setSolarenergy(double solarenergy) {
        this.solarenergy = solarenergy;
    }

    public double getUvindex() {
        return uvindex;
    }

    public void setUvindex(double uvindex) {
        this.uvindex = uvindex;
    }

    public double getSevererisk() {
        return severerisk;
    }

    public void setSevererisk(double severerisk) {
        this.severerisk = severerisk;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunriseEpoch() {
        return sunriseEpoch;
    }

    public void setSunriseEpoch(long sunriseEpoch) {
        this.sunriseEpoch = sunriseEpoch;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public long getSunsetEpoch() {
        return sunsetEpoch;
    }

    public void setSunsetEpoch(long sunsetEpoch) {
        this.sunsetEpoch = sunsetEpoch;
    }

    public double getMoonphase() {
        return moonphase;
    }

    public void setMoonphase(double moonphase) {
        this.moonphase = moonphase;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
