package utils;

import java.util.HashMap;
import java.util.Objects;

import io.qameta.allure.Attachment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AllureReport {

    @Attachment(value = "{0}")
    public static String saveText(String nomeArquivo, String message) {
        return message;
    }

    public static String attachInfoCliente(HashMap<String, String> valores) {
        final String mensagem = valores.entrySet().stream()
                        .filter(e -> Objects.nonNull(e.getValue()))
                        .map(entry -> entry.getKey().toUpperCase() + ": " + entry.getValue().toUpperCase() + "\n")
                        .reduce("", (a, b) -> a + b);

        return saveText("Dados Utilizado:", mensagem);
    }
    
}
