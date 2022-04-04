package com.example.persona.data;

import com.example.persona.model.ListModel;

import java.util.ArrayList;
import java.util.List;

public class ListData {
    public static List<ListModel> getlistData(){
        ListModel catatan1 = new ListModel("C++ Pemrograman", "12 Januari 2020", "C++ adalah bahasa pemrograman komputer yang dibuat oleh Bjarne Stroustrup, yang merupakan perkembangan dari bahasa C dikembangkan di Bell Labs.");
        ListModel catatan2 = new ListModel("Python Pemrograman", "31 Desember 2019", "Python adalah bahasa pemrograman tujuan umum yang ditafsirkan, tingkat tinggi. Dibuat oleh Guido van Rossum dan pertama kali dirilis pada tahun 1991, filosofi desain Python menekankan keterbacaan kode dengan penggunaan spasi putih yang signifikan.");
        ListModel catatan3 = new ListModel("C# Unity", "2 Agustus 2017", "C# merupakan sebuah bahasa pemrograman yang berorientasi objek yang dikembangkan oleh Microsoft sebagai bagian dari inisiatif kerangka .NET Framework");
        ListModel catatan4 = new ListModel("E - Commerce", "6 Maret 2020", "Perdagangan elektronik adalah penyebaran, pembelian, penjualan, pemasaran barang dan jasa melalui sistem elektronik seperti internet, televisi, dan jaringan komputer lainnya");
        ListModel catatan5 = new ListModel("C Pemrograman", "3 April 2018", "Bahasa pemrograman C merupakan salah satu bahasa pemrograman komputer. Dibuat pada tahun 1972 oleh Dennis Ritchie untuk Sistem Operasi Unix di Bell Telephone Laboratories.");
        ListModel catatan6 = new ListModel("Design Diagram", "18 September 2021", "Diagram adalah visualisasi dua dimensi yang merupakan sebuah representasi sebuah data. Diagram yang merepresentasikan atau nilai secara terorganisir.");

        List<ListModel> list = new ArrayList<>();
            list.add(catatan1);
            list.add(catatan2);
            list.add(catatan3);
            list.add(catatan4);
            list.add(catatan5);
            list.add(catatan6);
            return list;
    }
}
