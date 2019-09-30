package com.larry.java8Demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

enum Status {
	OPEN, CLOSE
}

@Data
class Task {
	private Status status;
	private int point;

	public Task(Status status, int point) {
		this.status = status;
		this.point = point;
	}

	public String toString() {
		return "Status:" + this.status + " Point:" + this.point;
	}
}

public class StreamTest2 {
	public static void main(String... args) {
		final List<Task> taskList = Arrays.asList(new Task(Status.CLOSE, 12), new Task(Status.OPEN, 15),
				new Task(Status.OPEN, 5));
		List<Task> closedTask = taskList.stream().filter(task -> task.getStatus() == Status.CLOSE)
				.collect(Collectors.toList());
		closedTask.stream().forEach(task->System.out.println(task));
		int totalPoinOpendt = taskList.stream().filter(task->task.getStatus()==Status.OPEN).mapToInt(Task::getPoint).sum();
		System.out.println("Total Point of OPEN:"+totalPoinOpendt);
		double totalPointsAll = taskList.stream().parallel().map(task->task.getPoint()).reduce(0, Integer::sum);
		System.out.println("Total Point of All:"+totalPointsAll);
	}

}
