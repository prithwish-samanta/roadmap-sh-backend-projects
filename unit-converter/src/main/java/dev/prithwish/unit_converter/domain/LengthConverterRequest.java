package dev.prithwish.unit_converter.domain;

public class LengthConverterRequest {
    private double length;
    private LengthUnit unitFromConvert;
    private LengthUnit unitToConvert;

    public LengthConverterRequest() {
    }

    public LengthConverterRequest(double length, LengthUnit unitFromConvert, LengthUnit unitToConvert) {
        this.length = length;
        this.unitFromConvert = unitFromConvert;
        this.unitToConvert = unitToConvert;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public LengthUnit getUnitFromConvert() {
        return unitFromConvert;
    }

    public void setUnitFromConvert(LengthUnit unitFromConvert) {
        this.unitFromConvert = unitFromConvert;
    }

    public LengthUnit getUnitToConvert() {
        return unitToConvert;
    }

    public void setUnitToConvert(LengthUnit unitToConvert) {
        this.unitToConvert = unitToConvert;
    }
}
