let students = [];

const $ = (selector) => document.querySelector(selector);
const byId = (value) => document.getElementById(value);

async function api(path, options = {}) {
  const res = await fetch(path, {
    headers: { "Content-Type": "application/json", ...(options.headers || {}) },
    ...options
  });

  const text = await res.text();
  let data = {};
  try { data = text ? JSON.parse(text) : {}; } catch (_) {}

  if (!res.ok) {
    throw new Error(data.error || `Server error (${res.status})`);
  }
  return data;
}

function toast(message, error = false) {
  const t = byId("toast");
  t.textContent = message;
  t.style.background = error ? "#b91c1c" : "#111827";
  t.classList.add("show");
  setTimeout(() => t.classList.remove("show"), 3000);
}

function esc(s) {
  return String(s).replace(/[&<>"']/g, c => ({
    "&":"&amp;", "<":"&lt;", ">":"&gt;", '"':"&quot;", "'":"&#039;"
  }[c]));
}

function table(rows) {
  if (!rows.length) return '<div class="empty">No students found.</div>';

  return `<table>
    <thead><tr>
      <th>ID</th><th>Student</th><th>Age</th><th>Course</th><th>Marks</th><th>Actions</th>
    </tr></thead>
    <tbody>
      ${rows.map(s => `<tr>
        <td>${s.id}</td>
        <td><strong>${esc(s.name)}</strong></td>
        <td>${s.age}</td>
        <td>${esc(s.course)}</td>
        <td>${Number(s.marks).toFixed(2)}</td>
        <td class="row-actions">
          <button onclick="editStudent(${s.id})">Edit</button>
          <button class="danger" onclick="deleteStudent(${s.id})">Delete</button>
        </td>
      </tr>`).join("")}
    </tbody>
  </table>`;
}

function render() {
  byId("totalStudents").textContent = students.length;

  const avg = students.length
    ? students.reduce((sum, s) => sum + Number(s.marks), 0) / students.length
    : 0;

  const max = students.length
    ? Math.max(...students.map(s => Number(s.marks)))
    : 0;

  byId("avgMarks").textContent = avg.toFixed(2);
  byId("maxMarks").textContent = max.toFixed(2);
  byId("studentTable").innerHTML = table(students);
  byId("recentTable").innerHTML = table([...students].reverse().slice(0, 5));
}

async function load() {
  students = await api("/api/students");
  render();
}

function show(section) {
  document.querySelectorAll(".section").forEach(x => x.classList.remove("active"));
  byId(section).classList.add("active");

  document.querySelectorAll(".nav-item").forEach(x =>
    x.classList.toggle("active", x.dataset.section === section)
  );

  byId("pageTitle").textContent =
    section === "add" ? "Add Student" : section.charAt(0).toUpperCase() + section.slice(1);
}

document.querySelectorAll(".nav-item").forEach(button => {
  button.onclick = () => show(button.dataset.section);
});

byId("quickAdd").onclick = () => {
  resetForm();
  show("add");
};

byId("viewAll").onclick = () => show("students");

byId("saveBtn").onclick = async () => {
  try {
    await api("/api/save", { method: "POST" });
    toast("Records saved successfully.");
  } catch (error) {
    toast(error.message, true);
  }
};

byId("search").oninput = event => {
  const query = event.target.value.toLowerCase().trim();
  byId("studentTable").innerHTML = table(
    students.filter(s => s.name.toLowerCase().includes(query))
  );
};

byId("studentForm").onsubmit = async event => {
  event.preventDefault();

  const editId = byId("editId").value.trim();

  const student = {
    id: Number(byId("id").value),
    name: byId("name").value.trim(),
    age: Number(byId("age").value),
    course: byId("course").value.trim(),
    marks: Number(byId("marks").value)
  };

  if (!student.id || !student.name || !student.age || !student.course ||
      Number.isNaN(student.marks) || student.marks < 0 || student.marks > 100) {
    toast("Please enter valid student details.", true);
    return;
  }

  try {
    if (editId) {
      await api(`/api/students/${editId}`, {
        method: "PUT",
        body: JSON.stringify(student)
      });
      toast("Student updated successfully.");
    } else {
      await api("/api/students", {
        method: "POST",
        body: JSON.stringify(student)
      });
      toast("Student added and saved successfully.");
    }

    await load();
    resetForm();
    show("students");
  } catch (error) {
    toast(error.message, true);
  }
};

byId("cancelBtn").onclick = () => {
  resetForm();
  show("students");
};

function resetForm() {
  byId("studentForm").reset();
  byId("editId").value = "";
  byId("id").disabled = false;
  byId("formTitle").textContent = "Add New Student";
}

window.editStudent = id => {
  const student = students.find(s => s.id === id);
  if (!student) return;

  byId("editId").value = student.id;
  byId("id").value = student.id;
  byId("id").disabled = true;
  byId("name").value = student.name;
  byId("age").value = student.age;
  byId("course").value = student.course;
  byId("marks").value = student.marks;
  byId("formTitle").textContent = "Update Student";
  show("add");
};

window.deleteStudent = async id => {
  if (!confirm("Delete this student?")) return;

  try {
    await api(`/api/students/${id}`, { method: "DELETE" });
    await load();
    toast("Student deleted successfully.");
  } catch (error) {
    toast(error.message, true);
  }
};

load().catch(error => toast("Could not load students: " + error.message, true));
