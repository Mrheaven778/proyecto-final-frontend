import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static void guardadDatos(String datos) {
        try (FileWriter myWriter = new FileWriter("champions.json");) {
            myWriter.write(datos);
            System.out.println("Se ha guardado el archivo");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
            e.printStackTrace();
        }
    }

    public static ArrayList<Campeon>  getChapeones() {
        try {
            ArrayList<Campeon> Listcampeones = new ArrayList<>();
            // Lee el contenido del archivo JSON
            FileReader fileReader = new FileReader("champions.json");

            // Convierte el contenido a una lista de mapas
            List<Map<String, Object>> campeones = parseJson(fileReader);

            // Itera sobre los elementos de la lista
            for (Map<String, Object> campeon : campeones) {
                // Accede a los datos del campeón
                String id = (String) campeon.get("id");
                String name = (String) campeon.get("name");
                String role = (String) campeon.get("role");
                String lane = (String) campeon.get("lane");
                String attackType = (String) campeon.get("attackType");
                String difficulty = (String) campeon.get("difficulty");
                String releaseYear = (String) campeon.get("releaseYear");
                String lore = (String) campeon.get("lore");

                Listcampeones.add(new Campeon(id, name, role, lane, attackType, difficulty, releaseYear, lore));
            }

            fileReader.close();
            return Listcampeones;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> parseJson(Reader reader) throws IOException {
        int c;
        StringBuilder jsonContent = new StringBuilder();

        // Lee el contenido del archivo
        while ((c = reader.read()) != -1) {
            jsonContent.append((char) c);
        }

        // Convierte el JSON a una lista de mapas
        String jsonString = jsonContent.toString();
        List<Map<String, Object>> campeones = new ArrayList<>();

        // Formato simple: cada campeón es un mapa
        jsonString = jsonString.substring(1, jsonString.length() - 1); // Elimina corchetes exteriores
        String[] campeonStrings = jsonString.split("},\\{");

        for (String campeonStr : campeonStrings) {
            String formattedCampeonStr = "{" + campeonStr + "}";
            Map<String, Object> campeon = parseJsonMap(formattedCampeonStr);
            campeones.add(campeon);
        }

        return campeones;
    }
    @SuppressWarnings("unchecked")
    private static Map<String, Object> parseJsonMap(String json) {
        // Formato simple: cada atributo es una cadena de caracteres
        String[] atributoStrings = json.split(",");
        Map<String, Object> atributos = new HashMap<>();

        for (String atributoStr : atributoStrings) {
            String[] keyValue = atributoStr.split(":", 2); // Cambio aquí: limitamos la división a 2 partes
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replaceAll("\"", "");
                String value = keyValue[1].trim().replaceAll("\"", "");
                atributos.put(key, value);
            }
        }

        return atributos;
    }
}
