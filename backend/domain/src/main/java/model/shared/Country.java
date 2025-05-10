package model.shared;

import lombok.NonNull;

import java.util.Locale;

public record Country(
        @NonNull String name,
        @NonNull String isoCountryCode) {
}
