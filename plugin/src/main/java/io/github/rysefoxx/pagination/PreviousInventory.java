package io.github.rysefoxx.pagination;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Map;

public record PreviousInventory(RyseInventory previousInventory, Map<String, Object> previousData) {

    @Override
    public String toString() {
        return "PreviousInventory{" +
                "previousInventory=" + previousInventory +
                ", previousData=" + previousData +
                '}';
    }
}
