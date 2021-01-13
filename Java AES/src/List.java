@SuppressWarnings("unused")
public class List {

    private int memory;
    private int length;
    private byte[] array;

    public List(byte... numbers) {
        array = numbers;
        length = numbers.length;
        memory = length + 1;
    }

    public int get(int i) {
        return array[i];
    }

    public void add(byte i) {
        if(length == memory) {
            resize((int) (length + Math.ceil(length / 2.0)));
        }

        array[length] = i;
        length++;
    }

    public void add(byte[] bytes) {
        for (byte b : bytes) {
            add(b);
        }
    }

    public void insert(int index, byte b) {
        if(index > length) throw new ArrayIndexOutOfBoundsException();
        byte[] list = new byte[++memory];
        int i = 0;

        for (int k = 0; k < array.length; i++) {
            if(k != index) {
                list[i] = array[k];
                k++;
            }
            else list[i] = b;
        }
        array = list;
        length++;
    }

    public void deleteIndex(int index) {
        byte[] list = new byte[--memory];
        int i = 0;

        for (int k = 0; k < array.length; k++) {
            if(k != index) {
                list[i] = array[k];
                i++;
            }
        }
        array = list;
        length--;
    }

    public void deleteValue(byte value) {
        for (int i = 0; i < array.length;) {
            if(array[i] == value) {
                deleteIndex(i);
                continue;
            }
            i++;
        }
    }

    public boolean inArray(byte value) {
        for(int item : array) {
            if(item == value) {
                return true;
            }
        }
        return false;
    }

    private void resize(int size) {
        byte[] list = new byte[size];
        System.arraycopy(array, 0, list, 0, array.length);
        array = list;
    }

    public int length() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < length; i++) {
            value.append(" ").append(array[i]).append(",");
        }
        if(value.length() > 0) value.deleteCharAt(value.length() - 1);
        return "[" + value.toString().trim() + "]";
    }
}
