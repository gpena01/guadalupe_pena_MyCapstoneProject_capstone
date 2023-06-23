const form = document.getElementById("todo-form");
const todoLane = document.getElementById("todo-lane");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const newTask = document.createElement("p");
  newTask.classList.add("task");
  newTask.setAttribute("draggable", "true");
  newTask.innerText = "";
  newTask.addEventListener("click", () => {
    editTask(newTask);
  })

  newTask.addEventListener("dragstart", () => {
    newTask.classList.add("is-dragging");
  });

  newTask.addEventListener("dragend", () => {
    newTask.classList.remove("is-dragging");
  });

  todoLane.appendChild(newTask);

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

function editTitle(element) {
  element.contentEditable = true;
  element.focus();
}

function confirmDelete(taskElement) {
  const shouldDelete = confirm("Are you sure you want to delete this task?");
  if (shouldDelete) {
  taskElement.remove();
  }
}