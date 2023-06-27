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

  const laneForm = document.createElement("form");
  laneForm.id = "todo-form";

  const addButton = document.createElement("button");
  addButton.type = "submit";
  addButton.classList.add("btn");
  addButton.innerHTML = "&#65291;";

  laneForm.appendChild(addButton);
  laneHeader.appendChild(columnName);
  laneHeader.appendChild(laneForm);
  newLane.appendChild(laneHeader);

  const taskList = document.createElement("div");
  taskList.classList.add("task-list");
  newLane.appendChild(taskList);

  lanesContainer.insertBefore(newLane, addLaneContainer);
}