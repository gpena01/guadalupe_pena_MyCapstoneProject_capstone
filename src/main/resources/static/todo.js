const form = document.getElementById("todo-form");
const todoLane = document.getElementById("todo-lane");
const taskList = todoLane.querySelector(".task-list");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const newTask = document.createElement("p");
  newTask.classList.add("task");
  newTask.setAttribute("draggable", "true");
  newTask.innerText = "";

  const taskContent = document.createElement("span");
  taskContent.innerText = "New Task";
  taskContent.addEventListener("click", () => {
    editTask(taskContent);
  });

  newTask.addEventListener("dragstart", () => {
    newTask.classList.add("is-dragging");
  });

  newTask.addEventListener("dragend", () => {
    newTask.classList.remove("is-dragging");
  });

  todoLane.appendChild(newTask);

  const deleteButton = document.createElement("button");
  deleteButton.classList.add("delete-button");
  deleteButton.innerHTML = "&#8722;";
  deleteButton.addEventListener("click", (event) => {
    event.stopPropagation(); // Prevent event bubbling to the task element
    confirmDelete(newTask);
  });

  newTask.appendChild(taskContent);
  newTask.appendChild(deleteButton);
  taskList.appendChild(newTask);

  form.reset();
});

function editHeading(element) {
  element.contentEditable = true;
  element.focus();
}

function editTask(element) {
  element.contentEditable = true;
  element.focus();
}

function confirmDelete(taskElement) {
  const shouldDelete = confirm("Are you sure you want to delete this task?");
  if (shouldDelete) {
    taskElement.remove();
  }
}