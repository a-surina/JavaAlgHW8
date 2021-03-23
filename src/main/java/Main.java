import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Задание 8.1: пример использования хеш-таблиц
        System.out.println("Примеры использования хэш-таблиц:\n" +
                "\t- Базы данных;\n" +
                "\t- Компиляторы;\n" +
                "\t- Ассоциативные массивы по типу 'ключ-значения';\n" +
                "\t- Каталоги для быстрого доступа к физическому расположению объекта\n" +
                "\t(например, в библиотеке или книжном магазине);\n" +
                "\t- Словари;\n" +
                "\t- Цифровая подпись;\n" +
                "\t- Magnet-ссылки\n" +
                "\t- Штрихкоды\n" +
                "\t- Прицнипы формирования автомобильных номеров (по коду региона);\n" +
                "\t- Хранение и передача логинов, паролей и номеров карт в большинстве надёжных систем;\n" +
                "\t- Формирование уникальных ссылок доступа (например, в конференцию Zoom;\n");

        //Задание 8.2: примеры ключей и коллизий
        System.out.println("Примеры ключей:\n" +
                "\t- Натуральные числа;\n" +
                "\t- Инициалы, начальные буквы;\n" +
                "\t- Сочетание букв и цифр;\n" +
                "\t- Целые слова (если отсутствует шифрование) - фамилия автора, например;\n" +
                "\t- Исходное значение, прошедшее шифрование по определенному правилу;\n");
        System.out.println("Примеры коллизий:\n" +
                "\t- Хэш-функция возвращает значение, которое ранеее уже было присвоено другому ключу;\n" +
                "\t- Среди авторов книг есть однофамильцы;\n" +
                "\t- Диапазон значений ключей изначально не предполагает, что все ключи уникальны;\n");

        //Задание 8.3: популярных и эффективных хеш-функций
        System.out.println("Примеры хеш-функций:\n" +
                "\t- SHA-2 (криптографический алгоритм Агентства национальной безопасности США;\n" +
                "\t- Хеш-сумма;\n" +
                "\t- Контрольные цифры и контрольная сумму (для верификации в документах, банковских картах);\n");

        /*Задание 8.5: хеш-таблица - открытая адресация, метод линейного пробирования
        В таблице храним номера квартир и имя владельца.
        В качестве ключа - номер квартиры, который хешируем.*/

        Flat aFlat;
        int aKey;
        String anOwner;
        int size = 67;

        HashTable hTable = new HashTable(size);

        Random rand = new Random();
        Faker faker = new Faker(new Locale("fi", "FI"));

        for (int i = 0; i < 65; i++) {
            aKey = rand.nextInt(999);
            anOwner = faker.name().lastName() + " " + faker.name().firstName();
            aFlat = new Flat(aKey, anOwner);
            hTable.insert(aFlat);
        }

        System.out.println("Some values have been inserted into the table:");
        System.out.println("203: " + hTable.insert(new Flat(203, "Torvalds Linus"))); //hash = 5
        System.out.println("467: " + hTable.insert(new Flat(467, "Haapasalo Ville"))); //hash = 5, но добавится на другое место
        hTable.fullPrinter();
        System.out.println();

        System.out.println("Some values have been been deleted from the table:");
        System.out.println("467: " + hTable.delete(467)); //true
        System.out.println("467: " + hTable.delete(467)); //false
        System.out.println("3: " + hTable.delete(1003) + "\n"); //false
        System.out.println("The table is full - rehashing is performed:");
        System.out.println("999: " + hTable.insert(new Flat(999, "Kaurismäki Aki")) + "\n"); //не добавится, т.к. список переполнен
        hTable.onlyFlatsPrinter();
        System.out.println();

        System.out.println("Let's find some flats:");
        System.out.println(hTable.find(203).printer());
        System.out.println(hTable.find(1003).printer() + "\n"); //doesn't exist

        //Задание 8.6: алгоритм двойного хеширования

    }
}
