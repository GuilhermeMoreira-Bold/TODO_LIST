package com.example.todo.odernation;

import com.example.todo.Tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RadixSort{
    private ArrayList<Task> positive = new ArrayList<>();
    private ArrayList<Task> negative = new ArrayList<>();

    private void radixHelper(List<Task> list) {
        for (Task number : list) {
            if (number.date >= 0) {
                positive.add(number);
            } else {
                negative.add(number);
            }
        }
    }

    private ArrayList<Task> radixDistribute(ArrayList<Task> list, int bitIndex) {
        if (list.isEmpty() || bitIndex < 0) {
            return list;
        }

        ArrayList<Task> bucketZero = new ArrayList<>();
        ArrayList<Task> bucketOne = new ArrayList<>();

        for (Task number : list) {
            long mask = 1L << bitIndex;
            if ((number.date & mask) == 0) {
                bucketZero.add(number);
            } else {
                bucketOne.add(number);
            }
        }

        ArrayList<Task> newOrders = new ArrayList<>();
        newOrders.addAll(radixDistribute(bucketZero, bitIndex - 1));
        newOrders.addAll(radixDistribute(bucketOne, bitIndex - 1));

        return newOrders;
    }
    public ArrayList<Task> sort(List<Task> lista) {
        if (lista == null) {
            throw new IllegalArgumentException("A lista de entrada não pode ser nula.");
        }

        radixHelper(lista);

        ArrayList<Task> sortedList = new ArrayList<>(lista.size());
        sortedList.addAll(radixDistribute(negative, 63));
        sortedList.addAll(radixDistribute(positive, 63));

        return sortedList;
    }
}
