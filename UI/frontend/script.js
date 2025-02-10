window.onload = async function() {
    try {
        const response = await fetch('http://localhost:8080/api/DirectClothing'); // Replace with your API endpoint
        const data = await response.text(); // Use .json() if response is JSON
        console.log("Received:", data);
        document.getElementById("message").innerText = data;
    } catch (error) {
        console.error("Error fetching data:", error);
    }
};
