const form = document.getElementById("todo-form");
const todoLane = document.getElementById("todo-lane");
const taskList = todoLane.querySelector("task-list");

// variables for adding/deleting column
const board = document.querySelector("board");
const addLaneButton = document.getElementById("add-lane-btn");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const newTask = document.createElement("p");
  newTask.classList.add("task");
  newTask.setAttribute("draggable", "true");
  newTask.innerText = "";

  const taskContent = document.createElement("p");
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

// edit column heading
function editHeading(element) {
  element.contentEditable = true;
  element.focus();
}

// edit card content
function editTask(element) {
  element.contentEditable = true;
  element.focus();
}

// edit kanban board title
function editTitle(element) {
  element.contentEditable = true;
  element.focus();
}

// confirms deletion of card
function confirmDelete(taskElement) {
  const shouldDelete = confirm("Are you sure you want to delete this task?");
  if (shouldDelete) {
    taskElement.remove();
  }
}