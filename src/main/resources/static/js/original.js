const form = document.getElementById("todo-form");
const todoLane = document.getElementById("todo-lane");
const taskList = todoLane.querySelector("task-list");

form.addEventListener("submit", (e) => {
  e.preventDefault();

  const newTask = document.createElement("p");
  newTask.classList.add("task");
  newTask.setAttribute("draggable", "true");
  newTask.innerText = "New Task";
  newTask.addEventListener("click", () => {
    editTask(newTask);
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

const addLaneButton = document.getElementById("add-lane-btn");
const lanesContainer = document.querySelector(".lanes");
const addLaneContainer = document.getElementById("add-lane-container");

addLaneButton.addEventListener("click", () => {
  createNewLane();
});

function createNewLane() {
  const newLane = document.createElement("div");
  newLane.classList.add("swim-lane");

  const laneHeader = document.createElement("div");
  laneHeader.classList.add("lane-header");

  const columnName = document.createElement("h3");
  columnName.classList.add("columnName");
  columnName.innerText = "New Lane";
  columnName.addEventListener("click", () => {
    editHeading(columnName);
  });

  const deleteButton = document.createElement("button");
  deleteButton.classList.add("delete-lane-btn", "btn");
  deleteButton.innerHTML = "&#8722;";
  deleteButton.addEventListener("click", (event) => {
    event.stopPropagation();
    deleteLane(newLane);
  });

  laneHeader.appendChild(columnName);
  laneHeader.appendChild(deleteButton);
  newLane.appendChild(laneHeader);

  const taskList = newLane.querySelector(".task-list");

  lanesContainer.insertBefore(newLane, addLaneContainer);

  // Add event listeners for new swim-lane's tasks
  const draggables = newLane.querySelectorAll(".task");
  draggables.forEach((task) => {
    task.addEventListener("dragstart", () => {
      task.classList.add("is-dragging");
    });
    task.addEventListener("dragend", () => {
      task.classList.remove("is-dragging");
    });
  });

  // Update droppable zones to include new swim-lane
  const droppables = document.querySelectorAll(".swim-lane");
  droppables.forEach((zone) => {
    zone.addEventListener("dragover", (e) => {
      e.preventDefault();

      const bottomTask = insertAboveTask(zone, e.clientY);
      const curTask = document.querySelector(".is-dragging");

      if (!bottomTask) {
        zone.appendChild(curTask);
      } else {
        zone.insertBefore(curTask, bottomTask);
      }
    });
  });
}

const deleteLaneButtons = document.querySelectorAll(".delete-lane-btn");
for (const deleteLaneButton of deleteLaneButtons) {
  deleteLaneButton.addEventListener("click", (event) => {
    event.stopPropagation();
    deleteLane(deleteLaneButton.parentNode.parentNode);
  });
}

function deleteLane(lane) {
  const deleteColumn = confirm("Are you sure you want to delete this column?");
  if (deleteColumn) {
    lane.remove();
  }
}

const insertAboveTask = (zone, mouseY) => {
  const elements = zone.querySelectorAll(".task:not(.is-dragging)");

  let closestTask = null;
  let closestOffset = Number.NEGATIVE_INFINITY;

  elements.forEach((task) => {
    const { top } = task.getBoundingClientRect();

    const offset = mouseY - top;

    if (offset < 0 && offset > closestOffset) {
      closestOffset = offset;
      closestTask = task;
    }
  });
  return closestTask;
};