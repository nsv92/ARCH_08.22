package flyweight;

import java.util.HashMap;

public class TeaMaker {

    protected HashMap<String, KarakTea> availableTea = new HashMap<>();

    public KarakTea makeTea(String preference) {

        if (!availableTea.containsKey(preference)) {
            availableTea.put(preference, new KarakTea());
        }
        return availableTea.get(preference);
    }
}
