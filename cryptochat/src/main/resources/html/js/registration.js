document.getElementById("registrationForm").addEventListener("submit", function (event) {
    const password = document.getElementById("password").value;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if (passwordRegex.test(password)) {
        alert("Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.");
        event.preventDefault(); // Prevent the form from submitting
    } else {
        // Implement AJAX request to handle form submission securely
        fetch("/register", {
            method: "POST",
            body: JSON.stringify({
                username: document.getElementById("username").value,
                password: password
            }),
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            if (response.ok) {
                // Registration successful, redirect to login page
                window.location.href = "/login.html";
            } else {
                // Registration failed, show error message
                document.getElementById("alert-message").classList.remove("d-none");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById("alert-message").classList.remove("d-none");
        });
    }
});
