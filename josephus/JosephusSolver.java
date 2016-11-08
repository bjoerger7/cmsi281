import java.util.Iterator;

public class JosephusSolver {

  public static void main(String[] args) {
    int k1 = 5;
    String [] test1 = { "Fido", "Delilah", "Rugrat", "Joshua", "Sid",
                        "Bow-wow", "Bogo", "Pogo", "Mimi", "Chloe" };
    CircularArrayList arrayList = new CircularArrayList(test1);
    for (CircularIterator i = arrayList.iterator(); !i.oneElementLeft(); ) {
        i.removeKthElement(k1);
    }
    System.out.println(arrayList.first());
    assert arrayList.first() == "Rugrat";

    CircularLinkedList linkedList = new CircularLinkedList(test1);
    for (CircularIterator i = linkedList.iterator(); !i.oneElementLeft(); ) {
        i.removeKthElement(k1);
    }
    System.out.println(linkedList.first());
    assert linkedList.first() == "Rugrat";
  }
}
