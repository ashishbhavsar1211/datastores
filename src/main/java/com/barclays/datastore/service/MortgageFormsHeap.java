package com.barclays.datastore.service;

import com.barclays.datastore.model.MortgageForm;

public class MortgageFormsHeap {

        private MortgageForm[] Heap;
        private int size;
        private int maxsize;

        public MortgageForm[] getHeap() {
            return Heap;
        }

        public MortgageFormsHeap(int maxsize)
        {
            this.maxsize = maxsize;
            this.size = 0;
            Heap = new MortgageForm[this.maxsize];
        }

        private int parent(int pos)
        {
            return pos / 2;
        }


        private int leftChild(int pos)
        {
            return (2 * pos);
        }
        private int rightChild(int pos)
        {
            return (2 * pos) + 1;
        }

        private boolean isLeaf(int pos)
        {
            if (pos >= (size / 2) && pos <= size) {
                return true;
            }
            return false;
        }

        private void swap(int fpos, int spos)
        {
            MortgageForm tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        private void maxHeapify(int pos)
        {
            if (isLeaf(pos))
                return;

            if (Heap[pos].compareTo(Heap[leftChild(pos)]) < 0 ||
                    Heap[pos].compareTo(Heap[rightChild(pos)]) < 0) {

                if (Heap[leftChild(pos)].compareTo(Heap[rightChild(pos)]) > 0){
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                }
                else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }

        // Inserts a new element to max heap
        public void insert(MortgageForm element)
        {
            Heap[size] = element;

            // Traverse up and fix violated property
            int current = size;
            while (Heap[current].compareTo(Heap[parent(current)])>0) {
                swap(current, parent(current));
                current = parent(current);
            }
            size++;
        }
    }
