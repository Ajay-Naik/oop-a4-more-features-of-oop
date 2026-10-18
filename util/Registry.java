package dcst.util;

import dcst.model.Member;
import dcst.model.Reportable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Registry<T extends Member> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) { items.add(item); }

    public T findById(int id) {
        for (T t : items) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public List<T> getAll() { return items; }
    public int size() { return items.size(); }

    public static <E extends Reportable> void printAll(Collection<E> list) {
        for (E e : list) System.out.println("    " + e.getReportLine());
    }

    public static <E> E firstOf(List<E> list) {
        return list.isEmpty() ? null : list.get(0);
    }
}
