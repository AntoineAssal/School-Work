package Infrastructures.Main;

public class StructureList implements Cloneable {
    private class StructureNode {
        private Structure sObj;
        private StructureNode next;

        public StructureNode() {
            sObj = null;
            next = null;
        }

        public StructureNode(Structure obj, StructureNode next) {
            sObj = obj;
            this.next = next;
        }

        public void setsObj(Structure obj) {
            sObj = obj;
        }

        public void setNext(StructureNode next) {
            this.next = next;
        }

        public Structure getsObj() {
            return sObj;
        }

        public StructureNode getNext() {
            return next;
        }
    } // End of Node class

    private StructureNode head;

    public StructureList() {
        head = null;
    }

    public void addToStart(Structure obj) {
        head = new StructureNode(obj, head);
    }

    public void addAtEnd(Structure obj) {
        if (head == null)
            new StructureNode(obj, null);
        else {
            StructureNode temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = new StructureNode(obj, null);
        }
    }

    public void append(StructureList otherList) {
        if (head == null)
            head = otherList.head;
        else {
            StructureNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = otherList.head;
        }
        otherList.head = null;
    }

    public Object clone() {
        try {
            StructureList newLst = (StructureList) super.clone();
            StructureNode temp = head;
            if (temp == null)
                newLst.head = null;
            else
                newLst.head = copyList(temp);
            return newLst;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public StructureNode copyList(StructureNode node) {
        StructureNode temp = node;
        StructureNode newHead = null;
        StructureNode end = null;

        newHead = new StructureNode(temp.sObj, null);
        end = newHead;
        temp = temp.next;

        while (temp != null) {
            end.next = new StructureNode(temp.sObj, null);
            end = end.next;
            temp = temp.next;
        }
        return newHead;
    }


    public void showListContents() {
        StructureNode temp = head;
        if (temp == null)
            System.out.println("There is nothing to display list is empty");
        else System.out.println("Contents of list: ");
        while (temp != null) {
            System.out.println(temp.sObj + " ===>\n");
            temp = temp.next;
        }
        System.out.println("X");
    }
}
