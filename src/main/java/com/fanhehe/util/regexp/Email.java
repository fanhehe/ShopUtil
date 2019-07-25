package com.fanhehe.util.regexp;

public enum Email {
    BASE("通配", "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

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

    Email(String label, String regexp) {
        this.label = label;
        this.regexp = regexp;
    }
}

