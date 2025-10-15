package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            for (String line : Files.readAllLines(Path.of(fromFileName))) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }

                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals("supply")) {
                    supply += amount;
                } else if (operation.equals("buy")) {
                    buy += amount;
                }
            }

            int result = supply - buy;

            String report = String.format("supply,%d%nbuy,%d%nresult,%d", supply, buy, result);

            Files.writeString(Path.of(toFileName), report);

        } catch (IOException e) {
            throw new RuntimeException("Can't process file: " + fromFileName, e);
        }
    }
}
