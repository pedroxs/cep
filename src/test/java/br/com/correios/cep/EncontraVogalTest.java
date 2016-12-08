package br.com.correios.cep;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EncontraVogalTest {

    /**
     * Usando um Iterator pois tem interface similar a 'Stream" do enunciado
     * <pre>
     *     public interface Stream {
     *         public char getNext();
     *         public boolean hasNext();
     *     }
     * </pre>
     */
    private Iterator<String> fixture = Arrays.stream("aAbBABacafeasd".split("")).iterator();

    @Test
    public void encontraVogal() throws Exception {
        LinkedHashMap<String, Integer> work = new LinkedHashMap<>();
        String isVogal = "(?i)[aeiou]";
        String notVogal = "(?i)[^aeiou]";
        LinkedList<String> conjunto = new LinkedList<>();

        while (fixture.hasNext()) {
            conjunto.add(fixture.next());
        }

        for (int i = 0; i < conjunto.size() - 3; i++) {
            List<String> temp = conjunto.subList(i, i + 3);
            if (temp.get(0).matches(isVogal) && temp.get(1).matches(notVogal) && temp.get(2).matches(isVogal)) {
                String join = String.join("", temp);
                Integer ocorrencias = work.getOrDefault(join, 0);
                work.put(join, ocorrencias + 1);
            }
        }
        System.out.println("todos os trios = " + work);
        Optional<Map.Entry<String, Integer>> first = work.entrySet().stream().filter(entry -> entry.getValue().equals(1)).findFirst();
        System.out.println("resultado = " + first);
    }
}