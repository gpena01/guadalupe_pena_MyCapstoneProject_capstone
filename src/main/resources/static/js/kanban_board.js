// Initialize the board
let board = {
  title: "Kanban Board",
  columns: []
};

// Load board from local storage if available
if (localStorage.getItem("board")) {
  board = JSON.parse(localStorage.getItem("board"));
}

const boardTitle = document.getElementById("board-title");
const columnsContainer = document.getElementById("columns");
const addColumnBtn = document.getElementById("add-column");
const newBoardBtn = document.getElementById("new-board");

// Render the board
function renderBoard() {
  boardTitle.textContent = board.title;
  columnsContainer.innerHTML = "";

  board.columns.forEach((column, columnIndex) => {
    const columnElement = createColumnElement(column, columnIndex);
    columnsContainer.appendChild(columnElement);
  });

  updateLocalStorage();
}

// Create column element
function createColumnElement(column, columnIndex) {
  const columnElement = document.createElement("div");
  columnElement.classList.add("column");

  const columnHeader = document.createElement("div");
  columnHeader.classList.add("column-header");

  const columnTitle = document.createElement("input");
  columnTitle.classList.add("column-title");
  columnTitle.value = column.title;
  columnTitle.addEventListener("input", () => {
    column.title = columnTitle.value;
    updateLocalStorage();
  });
  columnHeader.appendChild(columnTitle);

  const ellipsisIcon = document.createElement("i");
  ellipsisIcon.classList.add("fa", "fa-ellipsis-v", "column-ellipsis");
  columnHeader.appendChild(ellipsisIcon);

  // Create the menu container
  const menuContainer = document.createElement("div");
  menuContainer.classList.add("menu-container");

  // Create the edit link
  const editLink = document.createElement("a");
  editLink.innerHTML = `Edit <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
  <path d="M290.74 93.24l128.02 128.02-277.99 277.99-114.14 12.6C11.35 513.54-1.56 500.62.14 485.34l12.7-114.22 277.9-277.88zm207.2-19.06l-60.11-60.11c-18.75-18.75-49.16-18.75-67.91 0l-56.55 56.55 128.02 128.02 56.55-56.55c18.75-18.76 18.75-49.16 0-67.91z"/></svg>`;
  editLink.href = "#"; // Add your edit link destination here
  editLink.classList.add("menu-link");
  menuContainer.appendChild(editLink);
  // Add an event listener for the edit link if needed

  // Create the delete link
  const deleteLink = document.createElement("a");
  deleteLink.innerHTML = 'Delete <i>&#x2715;</i>';
  deleteLink.href = "#"; // Add your delete link destination here
  deleteLink.classList.add("menu-link");
  menuContainer.appendChild(deleteLink);

  // Add an event listener for the delete link if needed
  deleteLink.addEventListener("click", () => {
    deleteColumn(columnIndex);
    menuContainer.classList.remove("show-menu"); // Close the menu container after deletion
  });

  columnElement.appendChild(columnHeader);
  columnElement.appendChild(menuContainer);

  // Add event listener for ellipsis icon click
  ellipsisIcon.addEventListener("click", (event) => {
    event.stopPropagation(); // Prevent click event from bubbling up to document

  // Toggle the visibility of the menu container
    menuContainer.classList.toggle("show-menu");
  });

  // Close menu container when clicking outside of it
  document.addEventListener("click", (event) => {
    if (!columnHeader.contains(event.target)) {
      menuContainer.classList.remove("show-menu");
    }
  });

  columnElement.appendChild(columnHeader);

  const columnTasks = document.createElement("div");
  columnTasks.classList.add("column-tasks");
  column.tasks.forEach((task, taskIndex) => {
    const taskElement = createTaskElement(task, taskIndex, columnIndex);
    columnTasks.appendChild(taskElement);
  });
  columnElement.appendChild(columnTasks);

  const addTaskBtn = document.createElement("div");
  addTaskBtn.classList.add("add-task");
  addTaskBtn.textContent = "+ Add Task";
  addTaskBtn.addEventListener("click", () => {
    addTask(columnIndex);
  });
  columnElement.appendChild(addTaskBtn);

  return columnElement;
}

// Create task element
function createTaskElement(task, taskIndex, columnIndex) {
  const taskElement = document.createElement("div");
  taskElement.classList.add("task");
  taskElement.style.backgroundColor = task.backgroundColor || getDefaultBackgroundColor(taskIndex);

  const taskContent = document.createElement("div");
  taskContent.textContent = task.content;
  taskContent.contentEditable = true;
  taskContent.addEventListener("input", () => {
    task.content = taskContent.textContent;
    task.lastUpdate = new Date();
    updateLocalStorage();
  });
  taskElement.appendChild(taskContent);

  const taskDetails = document.createElement("div");
  taskDetails.classList.add("task-details");

  // Add an empty line (line break) element
  const lineBreak = document.createElement("br");
  taskDetails.appendChild(lineBreak);

  // Create a span for the "Last Updated: " label
  const lastUpdatedLabel = document.createElement("span");
  lastUpdatedLabel.textContent = "Last Updated: ";
  taskDetails.appendChild(lastUpdatedLabel);

  // Create a span for the timestamp
  const timestamp = document.createElement("span");
  timestamp.textContent = formatDate(task.lastUpdated);
  taskDetails.appendChild(timestamp);

  taskElement.appendChild(taskDetails);

  const deleteTaskBtn = document.createElement("span");
  deleteTaskBtn.innerHTML = `
  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-circle" viewBox="0 0 16 16">
    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
    <path d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z"/>
  </svg>
  `;
  deleteTaskBtn.classList.add("delete-task");
  deleteTaskBtn.addEventListener("click", () => {
    deleteTask(columnIndex, taskIndex);
  });

  // Create a container for the delete task button
  const deleteTaskContainer = document.createElement("div");
  deleteTaskContainer.classList.add("delete-task-container");

  // Append the delete task button to the container
  deleteTaskContainer.appendChild(deleteTaskBtn);

  // Append the delete task container to the task element
  taskElement.appendChild(deleteTaskContainer);

  taskElement.draggable = true;
  taskElement.addEventListener("dragstart", (event) => {
    event.dataTransfer.setData("text/plain", columnIndex + "," + taskIndex);
  });
  taskElement.addEventListener("dragover", (event) => {
    event.preventDefault();
  });
  taskElement.addEventListener("drop", (event) => {
    const data = event.dataTransfer.getData("text/plain").split(",");
    const sourceColumnIndex = parseInt(data[0]);
    const sourceTaskIndex = parseInt(data[1]);
    moveTask(sourceColumnIndex, sourceTaskIndex, columnIndex, taskIndex);
  });

  return taskElement;
}

function getDefaultBackgroundColor(taskIndex) {
  const defaultColors = ["rgba(0, 206, 209, 0.8)", "rgba(255, 20, 147, 0.7)", "rgba(255, 165, 0, 0.7)"];
  const colorIndex = taskIndex % defaultColors.length;
  return defaultColors[colorIndex];
}

// Add a new column
function addColumn() {
  const column = {
    title: "New Column",
    tasks: []
  };
  board.columns.push(column);
  renderBoard();
}

// Delete a column
function deleteColumn(columnIndex) {
  const confirmDelete = confirm("Are you sure you want to delete this column?");

  if (confirmDelete) {
    board.columns.splice(columnIndex, 1);
    renderBoard();
  }
}

// Add a new task
function addTask(columnIndex) {
  const task = {
    content: "New Task",
    lastUpdated: new Date()
  };
  board.columns[columnIndex].tasks.push(task);
  renderBoard();
}

// Delete a task
function deleteTask(columnIndex, taskIndex) {
  const confirmDelete = confirm("Are you sure you want to delete this task?");

  if(confirmDelete) {
    board.columns[columnIndex].tasks.splice(taskIndex, 1);
    renderBoard();
  }
}

// Move a task to a different column
function moveTask(sourceColumnIndex, sourceTaskIndex, targetColumnIndex, targetTaskIndex) {
  const task = board.columns[sourceColumnIndex].tasks.splice(sourceTaskIndex, 1)[0];
  board.columns[targetColumnIndex].tasks.splice(targetTaskIndex, 0, task);
  renderBoard();
}

// Format date to "YYYY-MM-DD HH:MM:SS" format
function formatDate(date) {
  const options = {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit"
  };
  return date.toLocaleString(undefined, options);
}

// Update board data in local storage
function updateLocalStorage() {
  localStorage.setItem("board", JSON.stringify(board));
}

// Event listeners
addColumnBtn.addEventListener("click", addColumn);
newBoardBtn.addEventListener("click", () => {
  localStorage.removeItem("board");
  board = {
    title: "Kanban Board",
    columns: []
  };
  renderBoard();
});

// Initial render
renderBoard();