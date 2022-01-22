package me.osmanfurkan906.upgradeplugin.model;

import me.osmanfurkan906.upgradeplugin.utils.Utils;

public enum EconomyResponse {
    SUCCESS("&aYou successfully changed the player's wallet money"), LIMIT("&cThis action doesn't meet the limits"),
    FAILURE("&cA problem has occurred while changing the money");

    private final String message;

    EconomyResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return Utils.colored(message);
    }
}
