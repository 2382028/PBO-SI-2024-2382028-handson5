import java.util.Scanner;

public class main {
    public static String[] todos = new String[3];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("BEFORE EDIT");
        addTodoList("Mewarnai");
        addTodoList("Membaca");
        addTodoList("Bersepeda");
        addTodoList("Berkhotbah");
        showTodoList();
        editToDoList(1, "Bekerja");

        System.out.println("AFTER EDIT");
        showTodoList();
    }

    public static void showTodoList() {
        System.out.println("TODO LIST");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todo != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }

    public static void addTodoList(String todo) {
        resizeArrayIfFull();
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    public static void resizeArrayIfFull() {
        Boolean isFull = isArrayFull();

        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    public static Boolean isArrayFull() {
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                return false;
            }
        }
        return true;
    }

    public static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    public static boolean removeToDoList(Integer number) {
        if (isSelectedToDoNotValid(number)) {
            return false;
        }

        for (int i = number - 1; i < todos.length; i++) {
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    
    public static boolean isSelectedToDoNotValid(Integer number) {
        if (number <= 0 || number - 1 > todos.length - 1 || todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editToDoList(Integer number, String newToDo) {
        if (isSelectedToDoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newToDo;
        return true;
    }

    public static String input(String info) {
        System.out.println(info + " : ");
        return scanner.nextLine();
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTodoList();
            System.out.println("Menu: ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("Pilih");
            switch (selectedMenu) {
                case "1":
                    showMenuTodoList();
                    break;
                case "2":
                    showMenuRemoveTodoList();
                    break;
                case "3":
                    showMenuEditTodoList();
                    break;
                case "4":
                    isRunning = false;
                    break;
            }
        }
    }

    public static void showMenuRemoveTodoList() {
        System.out.println("MENGHAPUS TODO LIST");
        String number = input("Nomor yang ingin dihapus (x jika batal)");
        if (number.equals("x")) {
            //batal
        } else {
            boolean success = removeToDoList(Integer.parseInt(number));
            if (!success) {
                System.out.println("Gagal menghapus todo list : " + number);
            }
        }
    }

    public static void showMenuTodoList() {
        System.out.println("MENAMBAH MENU TO DO LIST");
        String todo = input("Todo (x jika batal)");
        if (todo.equals("x")) {
            //batal
        } else {
            addTodoList(todo);
        }
    }

    public static void showMenuEditTodoList() {
        System.out.println("MENGEDIT TODO LIST");
        String selectedTodo = input("Nomor todo yang akan diubah (x jika batal)");
        if (selectedTodo.equals("x")) {
            return;
        }

        String newTodo = input("Masukkan Todo yang baru (x jika batal)");
        if (newTodo.equals("x")) {
            return;
        }

        boolean isEditToDoSuccess = editToDoList(Integer.parseInt(selectedTodo), newTodo);
        if (isEditToDoSuccess) {
            System.out.println("Berhasil mengedit todo");
        } else {
            System.out.println("Gagal mengedit todo");
        }
    }
}
