package com.fanhehe.util.regexp;

public enum Uid {
    BASE("通配", "\\d{4, 10}");

    private String label;
    private String regexp;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    Uid(String label, String regexp) {
        this.label = label;
        this.regexp = regexp;
    }
}

