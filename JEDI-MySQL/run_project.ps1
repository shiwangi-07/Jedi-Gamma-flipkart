$connectorPath = "D:\Downloads\mysql-connector-j-8.0.33\mysql-connector-j-8.0.33\mysql-connector-j-8.0.33.jar"

# Create bin directory if it doesn't exist
if (!(Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
    Write-Host "Created 'bin' directory."
}

# Collect all Java source files
Write-Host "Collecting source files..."
Get-ChildItem -Recurse -Filter *.java src | Select-Object -ExpandProperty FullName > sources.txt

# Compile the project
Write-Host "Compiling project..."
javac -d bin -cp $connectorPath "@sources.txt"

# Copy resources (properties files, etc.)
if ($?) {
    Write-Host "Copying resources..."
    Get-ChildItem -Path src -Recurse -Exclude *.java | Copy-Item -Destination { $_.FullName.Replace("$PWD\src", "$PWD\bin") } -Force
}

# Run the project if compilation was successful
if ($?) {
    Write-Host "Compilation successful. Starting application..."
    Write-Host "------------------------------------------------"
    java -cp "bin;$connectorPath" com.flipkart.client.FlipFitApplication
} else {
    Write-Error "Compilation failed. Please check the errors above."
}
