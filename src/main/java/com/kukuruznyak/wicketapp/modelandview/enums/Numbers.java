package com.kukuruznyak.wicketapp.modelandview.enums;

public enum Numbers {
    ZERO("0"), ONE("1"), TWO("2"), TREE("3"),
    FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), DOT("."), DOUBLE_ZERO("00");

    private String name;

    Numbers(String name) {
        this.name = name;
    }

    public static Numbers getNumber(String number) {
        for (Numbers value : values()) {
            if (value.toString().equals(number)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
