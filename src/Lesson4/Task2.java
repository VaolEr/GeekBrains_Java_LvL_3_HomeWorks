package Lesson4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task2 {

    // ExecutorService  полезен для чата, так как нужно дать серверу возможность распарраллеливать аутентификации
    // клиентов, так как возможны многочисленные единовременные подключения. Для сервера, я думаю, имеет смысл
    // использовать Executors.newFixedThreadPool(), где количесто единовременных подключений нужно эмпирическим путём установить,
    // исходя из количества пользователей, либо newCachedThreadPool(), чтоб не завязываться на пользователей, но бесконечное создание потоков
    // может привести к чрезмерному потреблению оперативной памяти, на сколько я понимаю. Поэтому, я бы использовал
    // Executors.newFixedThreadPool(). На сколько я понимаю, в рамках нами написанного чата необходимо
    // испльзовать ExecutorService в момент ожидания новых подключений к серверу: waitAndProcessNewClient().

}