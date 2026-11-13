import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HobbyManager {
    private final List<String> hobbies = new ArrayList<>();
    private final Random random = new Random();
    private int drawCount;

    public boolean addHobby(String name) {
        String hobby = name.trim();
        if (hobby.isEmpty()) {
            return false;
        }
        hobbies.add(hobby);
        return true;
    }

    public List<String> listHobbies() {
        return new ArrayList<>(hobbies);
    }

    public String drawHobby() {
        int selectedIndex = random.nextInt(hobbies.size());
        drawCount++;
        return hobbies.get(selectedIndex);
    }

    public int getDrawCount() {
        return drawCount;
    }
}
