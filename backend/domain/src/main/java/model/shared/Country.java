package model.shared;

import lombok.NonNull;

public record Country(
        @NonNull String name,
        @NonNull String isoCountryCode) {
}
