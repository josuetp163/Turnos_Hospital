package ec.edu.espol.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularSimplyLinkedList<E> implements Iterable<E>{

    private Node<E> last;
    private int current;

    public CircularSimplyLinkedList() {
        last = null;
        current = 0;
    }

    private class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = this;
        }
    }

    //MÉTODOS PROPIOS
    private Node<E> getPrevious(Node<E> p) {
        if (isEmpty() || p == null) return null;
        Node<E> q = last;
        do {
            if (q.next == p) return q;
            q = q.next;
        } while (q != last);
        return null;
    }

    private Node<E> getNodeByIndex(int index) {
        if (index == current - 1) return last;
        Node<E> q = last.next;
        for (int i = 0; i < index; i++)
            q = q.next;
        return q;
    }

    //MÉTODOS DE LA LISTA
    public boolean addFirst(E e) {
        Node<E> n = new Node<>(e);
        if (e == null) return false;
        else if (isEmpty()) {
            last = n;
            n.next = n;
        } else {
            n.next = last.next;
            last.next = n;
        }
        current++;
        return true;
    }

    public boolean addLast(E e) {
        Node<E> n = new Node<>(e);
        if (e == null) return false;
        else if (isEmpty()) {
            last = n;
            n.next = n;
        } else {
            n.next = last.next;
            last.next = n;
            last = n;
        }
        current++;
        return true;
    }

    public E getFirst() {
        if (isEmpty()) throw new IllegalStateException("La lista está vacía");
        return last.next.data;
    }

    public E getLast() {
        if (isEmpty()) throw new IllegalStateException("La lista está vacía");
        return last.data;
    }

    public int indexOf(E e) {
        if (e == null || isEmpty()) return -1;
        Node<E> q = last;
        int cont = 0;
        do {
            if (q.next.data.equals(e))
                return cont;
            q = q.next;
            cont++;
        } while (q != last);
        return -1;
    }

    public int size() {
        return current;
    }

    public boolean removeLast() {
        if (isEmpty()) return false;
        if (last == last.next) {
            last.data = null;
            last = last.next = null;
        } else {
                Node<E> prev = getPrevious(last);
                if(prev != null) {
                    prev.next = last.next;
                    last.next = null;
                    last.data = null; //help GC
                    last = prev;
                }
        }
        current--;
        return true;
    }

    public boolean removeFirst() {
        if (isEmpty()) return false;
        if (last == last.next) {
            last.data = null;
            last = last.next = null;
        } else {
            Node<E> tmp = last.next;
            last.next = tmp.next;
            tmp.next = null;
            tmp.data = null; //help GC
        }
        current--;
        return true;
    }

    public boolean insert(int index, E e) {
        if (index < 0 || index > current || e == null) return false;
        if (index == 0) {
            return addFirst(e);
        } else if (index == current) {
            return addLast(e);
        } else {
            Node<E> n = new Node<>(e);
            Node<E> nlast = last;
            for (int i = 0; i < index; i++)
                nlast = nlast.next;
            n.next = nlast.next;
            nlast.next = n;
            current++;
            return true;
        }
    }

    public boolean set(int index, E e) {
        if (e == null || index < 0 || index >= current) return false;
        getNodeByIndex(index).data = e;
        return true;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public E get(int index) {
        if (index < 0 || index >= current) throw new IndexOutOfBoundsException();
        return getNodeByIndex(index).data;
    }

    public boolean contains(E e) {
        if (e == null || isEmpty()) return false;
        Node<E> p = last.next;
        do {
            if (p.data.equals(e))
                return true;
            p = p.next;
        } while (p != last.next);
        return false;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= current) return false;
        if (index == 0)
            return removeFirst();
        else if (index == current - 1)
            return removeLast();
        else {
            Node<E> prev = getNodeByIndex(index - 1);
            Node<E> n = prev.next;
            prev.next = n.next;
            n.next = null;
            n.data = null; //help GC
            current--;
            return true;
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<E> p = last.next; p != last; p = p.next)//hasta el penultimo
        {
            sb.append(p.data);
            sb.append(",");
        }
        sb.append(last.data);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            Node<E> n = last.next;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public E next() {
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                    E tmp = n.data;
                    n = n.next;
                    return tmp;
            }
        };
        return it;
    }
}
