package com.mycom.word;

import java.io.*;
import java.util.*;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;
    final String fName = "Dictionary.txt";

    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.print("뜻 입력: ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addWord() {
        Word one = (Word) add();
        list.add(one);
        System.out.println("\n새 단어가 단어장에 추가되었습니다 !!!\n");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    public void listAll() {
        System.out.print("\n--------------------------------\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------\n");
    }

    public void listAll(int level) {
        int j = 0;
        System.out.print("\n--------------------------------\n");
        for (int i = 0; i < list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if (ilevel != level) {
                continue;
            }
            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------\n");
    }

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idList = new ArrayList<>();
        int j = 0;
        System.out.print("--------------------------------\n");
        for (int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if (!word.contains(keyword)) {
                continue;
            }
            System.out.print((j + 1) + " ");
            System.out.println(list.get(i).toString());
            idList.add(i);
            j++;
        }
        System.out.println("--------------------------------");
        return idList;
    }

    public void updateItem() {
        System.out.print("\n=> 수정할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idList = this.listAll(keyword);

        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();

        System.out.print("=> 뜻 입력 : ");
        String meaning = s.next();

        Word update = list.get(idList.get(id - 1));
        update.setMeaning(meaning);

        System.out.println("\n단어 수정이 성공적으로 되었습니다!!\n");
    }

    public void deleteItem() {
        System.out.print("\n=> 삭제할 단어 검색 : ");
        String keyword = s.next();

        ArrayList<Integer> idList = this.listAll(keyword);

        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();

        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String deleteAgree = s.next();

        if (deleteAgree.equalsIgnoreCase("y") || deleteAgree.equalsIgnoreCase("Y")) {
            list.remove(idList.get(id - 1));
            System.out.println("\n선택한 단어 삭제 완료!!\n");
        } else {
            System.out.println("취소되었습니다.");
        }
    }
}