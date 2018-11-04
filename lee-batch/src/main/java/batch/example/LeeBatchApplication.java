package batch.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import batch.example.domain.Todo;
import batch.example.mapper.TodoMapper;

@SpringBootApplication
//@MapperScan("batch.example.mapper")
public class LeeBatchApplication implements CommandLineRunner { // CommandLineRunnerを実装する

    public static void main(String[] args) {
    	System.exit(SpringApplication.exit(SpringApplication.run(LeeBatchApplication.class, args)));
//    	System.err.println(SpringApplication.exit(SpringApplication.run(LeeBatchApplication.class, args)));
    }

    @Autowired
    private TodoMapper todoMapper;

//    public LeeBatchApplication(TodoMapper todoMapper) {
//        this.todoMapper = todoMapper; // Mapperをインジェクションする
//    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

    	Todo newTodo = new Todo();
        newTodo.setId(0);
        newTodo.setTitle("飲み会");
        newTodo.setDetails("銀座 19:00");

        try {
        	if (todoMapper.selectAll().isEmpty()) {
                todoMapper.insert(newTodo); // 新しいTodoをインサートする
        	}
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}

        try {
            todoMapper.selectAll().forEach(todo->{
              System.out.println("ID       : " + todo.getId());
              System.out.println("TITLE    : " + todo.getTitle());
              System.out.println("DETAILS  : " + todo.getDetails());
              System.out.println("FINISHED : " + todo.isFinished());
            });
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}
    }

}