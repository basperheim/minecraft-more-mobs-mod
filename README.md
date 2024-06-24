# Minecraft More Mobs Mod

## Getting Started

Let's walk through the process of creating a simple Minecraft mod that increases enemy mob spawns using Kotlin and Gradle. We'll cover setting up the mod's structure, writing the necessary Kotlin code, and configuring your build files.

### Step-by-Step Guide to Creating a Minecraft Mod

#### 1. Project Structure

Ensure your project structure is as follows:

```
minecraft-more-mobs-mod/
├── build.gradle.kts
├── settings.gradle.kts
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── MyMod.kt      // Main mod class
│   │   └── resources/
│   │       └── META-INF/
│   │           └── mods.toml         // Mod metadata
└── gradle/
    └── wrapper/
        ├── gradle-wrapper.jar
        └── gradle-wrapper.properties
```

#### 2. Configure `build.gradle.kts`

Edit your `build.gradle.kts` file to include the necessary dependencies and plugins for Minecraft Forge and Kotlin:

```kotlin
plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
    id("net.minecraftforge.gradle") version "5.1.22"
}

group = "com.example"
version = "1.0-SNAPSHOT"
archivesBaseName = "more-mobs-mod"

repositories {
    mavenCentral()
    maven(url = "https://files.minecraftforge.net/maven")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    minecraft("net.minecraftforge:forge:1.21-51.0.17")
}

minecraft {
    mappings("official", "1.21")
    runs {
        client {
            workingDirectory = project.file("run")
            property("forge.logging.console.level", "info")
        }
        server {
            workingDirectory = project.file("run")
            property("forge.logging.console.level", "info")
        }
    }
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                         "Implementation-Version" to project.version))
    }
}
```

#### 3. Create `mods.toml`

Create a `mods.toml` file under `src/main/resources/META-INF/` with the following content:

```toml
modLoader="javafml"
loaderVersion="[36,)"
license="MIT"
[[mods]]
modId="moremobsmod"
version="1.0"
displayName="More Mobs Mod"
description="A mod that increases the spawn rate of enemy mobs."
```

#### 4. Main Mod Class

Create a `MyMod.kt` file under `src/main/kotlin/com/example/` with the following content:

```kotlin
package com.example

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod("moremobsmod")
class MyMod {
    init {
        // Register the setup method for mod loading
        FMLJavaModLoadingContext.get().modEventBus.addListener(this::setup)
        FMLJavaModLoadingContext.get().modEventBus.addListener(this::doClientStuff)
        MinecraftForge.EVENT_BUS.register(this)
    }

    private fun setup(event: FMLCommonSetupEvent) {
        // Pre-initialization code
    }

    private fun doClientStuff(event: FMLClientSetupEvent) {
        // Client-side initialization code
    }

    @SubscribeEvent
    fun onEntitySpawn(event: EntityJoinWorldEvent) {
        // Increase spawn rate logic
        val entity = event.entity
        if (entity.isMonster) {
            // Example: double the spawn rate
            entity.world.addEntity(entity)
        }
    }
}
```

This code sets up a basic mod structure and includes a simple event handler to increase the spawn rate of enemy mobs.

#### 5. Build and Run Your Mod

1. **Build the Project:**

```bash
./gradlew build
```

2. **Run the Client:**

```bash
./gradlew runClient
```

3. **Test Your Mod:**

- Launch Minecraft using the Forge profile.
- Verify that enemy mobs are spawning at an increased rate as defined by your mod.

### Additional Resources

- **Forge Documentation:** [Minecraft Forge Documentation](https://mcforge.readthedocs.io/en/latest/)
- **Kotlin Documentation:** [Kotlin Documentation](https://kotlinlang.org/docs/reference/)

This should give you a good starting point for creating and testing your Minecraft mod that increases enemy mob spawns. If you encounter any issues or have specific questions, feel free to ask!

Creating mods for Minecraft, especially when using TLauncher, involves several steps and a mix of Java programming and configuration through JSON files. Here's a detailed overview to get you started:

### Overview of Minecraft Modding

See the [Minecraft Forge documentation](https://docs.minecraftforge.net/en/latest/gettingstarted/).

1. **Set Up Your Development Environment:**

- **Install JDK:** Ensure you have the Java Development Kit (JDK) installed on your system.
- **IDE:** Use an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.
- **Minecraft Forge:** Download and set up Minecraft Forge, which provides a modding API for Minecraft.

2. **Project Structure:**

Your mod will be a Java project with a specific structure. Here's a basic outline:

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── yourmod
│   │           └── MainClass.java
│   ├── resources
│       ├── META-INF
│       │   └── mods.toml
│       └── assets
│           └── yourmod
│               ├── blockstates
│               ├── lang
│               ├── models
│               │   ├── block
│               │   └── item
│               ├── textures
│                   ├── block
│                   └── item
```

3. **Creating the Mod:**

Main Class:

```java
package com.yourmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod("yourmodid")
public class MainClass {
    public MainClass() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Pre-initialization code
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Client-side initialization code
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // Server-side initialization code
    }
}
```

mods.toml:

```toml
modLoader="javafml"
loaderVersion="[36,)"
license="MIT"
[[mods]]
modId="yourmodid"
version="1.0.0"
displayName="Your Mod Name"
```

- **JSON Files:** These define the block/item models and other resources.

blockstates/block.json:

```json
{
  "variants": {
    "": { "model": "yourmod:block/blockname" }
  }
}
```

models/block/blockname.json:

```json
{
  "parent": "block/cube_all",
  "textures": {
    "all": "yourmod:block/blockname"
  }
}
```

4. **Compiling and Testing:**
   - Use your IDE to build the project into a JAR file.
   - Place the JAR file in the `mods` folder of your Minecraft directory (`~/Library/Application Support/minecraft/mods` for Mac).

### Understanding Your Minecraft Directory

- **.mca Files:**

  - These are Minecraft's region files, containing the saved world data.
  - They are located in directories like `DIM-1`, `DIM1`, `region`, and `poi`, representing different dimensions and parts of the world.
  - The format `.mca` stands for "Minecraft Anvil" and stores chunks of the world.

- **level.dat and other .dat files:**

  - `level.dat` contains global information about the world (seed, time, player inventory, etc.).
  - `.dat` files in `playerdata` store individual player data.

- **Java (JAR) Files:**
  - The JAR files you create for your mods are not part of the default Minecraft save structure.
  - They should be placed in the `mods` directory.

### Tips for Modding

- **Learn Java:** Understanding Java is crucial for Minecraft modding.
- **Read Documentation:** Familiarize yourself with the Forge documentation and Minecraft's data format specifications.
- **Start Simple:** Begin with small changes to understand how things work before creating complex mods.

This should give you a solid starting point for creating Minecraft mods. If you have specific questions or run into issues, there are many online communities and resources to help you along the way.

## Installing Gradle and using Forge in TLauncher

Great! You've successfully installed Minecraft Forge. Here's how you can proceed to open Minecraft Forge and generate boilerplate code for a Minecraft mod.

### Opening Minecraft Forge

1. **Open Minecraft Launcher:**

   - Open your Minecraft launcher (TLauncher in your case).
   - In the launcher, you should see a new profile named `Forge`. If not, you can create a new profile and select the Forge version you installed (1.21-51.0.17).

2. **Select Forge Profile:**
   - Select the `Forge` profile and click `Play`. This will launch Minecraft with the Forge mod loader.

### Generating Boilerplate Code for a Minecraft Mod

1. **Set Up Your Development Environment:**

   - **IDE:** Use IntelliJ IDEA, Eclipse, or another Java IDE. IntelliJ IDEA is recommended for its robust support for Gradle, which Forge uses.

2. **Create a New Project:**

   - Open your IDE and create a new Gradle project.
   - Set up the project structure to match the standard Forge mod structure.

3. **Configure `build.gradle`:**

   - Configure the `build.gradle` file to set up dependencies and project information. Here’s an example of what it might look like:

     ```groovy
     buildscript {
         repositories {
             maven { url = "https://maven.minecraftforge.net" }
             mavenCentral()
         }
         dependencies {
             classpath 'net.minecraftforge.gradle:ForgeGradle:5.1.10'
         }
     }

     apply plugin: 'net.minecraftforge.gradle'

     group = 'com.yourmod'
     version = '1.0'
     archivesBaseName = 'yourmod'

     minecraft {
         mappings channel: 'official', version: '1.21'
         runs {
             client {
                 workingDirectory project.file('run')
                 args '--username', 'Player'
             }
         }
     }

     repositories {
         maven { url = 'https://maven.minecraftforge.net' }
         mavenCentral()
     }

     dependencies {
         minecraft 'net.minecraftforge:forge:1.21-51.0.17'
     }

     sourceCompatibility = 1.8
     targetCompatibility = 1.8
     ```

4. **Create Mod Files:**

Create the main class for your mod. Here’s an example of a simple mod class:

```java
package com.yourmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("yourmodid")
public class YourMod {
    public YourMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Pre-initialization code
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Client-side initialization code
    }
}
```

5. **Create `mods.toml`:**

- This file provides metadata about your mod. Place it in `src/main/resources/META-INF/`.

```toml
modLoader="javafml"
loaderVersion="[36,)"
license="MIT"
[[mods]]
modId="yourmodid"
version="1.0"
displayName="Your Mod Name"
```

6. **Add Resources:**

- Add directories for block models, textures, etc., under `src/main/resources/assets/yourmod/`.

### Running Your Mod

1. **Run Configuration:**

- In your IDE, set up a run configuration to start the Minecraft client with your mod. Most IDEs will detect the Forge setup and provide a run configuration automatically.

2. **Build and Test:**

- Use the Gradle `build` task to compile your mod.
- Start the Minecraft client from your IDE to test your mod.

### Additional Tips

- **Forge Documentation:** Refer to the official [Minecraft Forge Documentation](https://mcforge.readthedocs.io/en/latest/) for detailed guides and examples.
- **Community Resources:** Join modding communities and forums to get help and share your progress.

Following these steps, you'll have a basic mod set up and running in Minecraft. From there, you can expand your mod by adding new features, items, blocks, and more.

## Set JAVA_HOME path

Based on the information you've provided, the correct path to set as your `JAVA_HOME` environment variable in your `~/.zshrc` file would be:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home
```

To set this in your `~/.zshrc` file and make it persistent:

1. Open your terminal.

2. Edit your `~/.zshrc` file using a text editor like `nano` or `vim`:

```bash
nano ~/.zshrc
```

3. Add the following line at the end of the file:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home
```

4. Save the file by pressing `Ctrl + O` (for nano) and then `Enter`.

5. Exit the editor by pressing `Ctrl + X`.

6. To apply the changes immediately, reload `~/.zshrc`:

```bash
source ~/.zshrc
```

7. Verify that `JAVA_HOME` is set correctly by echoing the variable:

```bash
echo $JAVA_HOME
```

8. Now, try running `gradle` again in your terminal. It should recognize the Java installation specified by `JAVA_HOME` and proceed without errors related to `JAVA_HOME`.

This setup ensures that your Java Development Kit (JDK) installation is correctly referenced by Gradle and other Java-based tools on your macOS system.

## Install Gradle

```bash
brew install gradle && brew install kotlin
```

Gradle itself is not an IDE but rather a build automation tool and dependency management system, commonly used for Java projects (including Minecraft mods). It helps automate the build process, manage dependencies, and execute tasks defined in your project's build script (`build.gradle`).

For developing Java applications, including Minecraft mods, you typically pair Gradle with an Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or even Visual Studio Code (VS Code) with appropriate plugins. Here's how you can proceed with using VS Code for your Gradle-based Minecraft mod development:

### Setting Up VS Code for Gradle and Minecraft Mod Development

1. **Install Visual Studio Code:**

- If you haven't already, download and install [Visual Studio Code](https://code.visualstudio.com/).

2. **Install Java Extension Pack:**

- Open VS Code and install the **Java Extension Pack**. This pack includes essential extensions for Java development, including support for Gradle.
- Open Extensions view in VS Code (Ctrl+Shift+X), search for "Java Extension Pack", and click Install.

3. **Create a New Gradle Project:**

- Open your terminal and navigate to the directory where you want to create your Minecraft mod project.

- Use Gradle to initialize a new project. For example, to create a basic Gradle project structure:

```bash
gradle init --type java-library
```

This command sets up a basic Java library project using Gradle.

4. **Open Your Project in VS Code:**

- Once the project is initialized, open VS Code.
- Use `File > Open...` to open the project directory (the one containing `build.gradle` and `src` directory).

5. **Configure VS Code for Gradle:**

- VS Code should automatically detect the Gradle project structure and configure itself accordingly.
- You might need to configure the JDK and Gradle version in VS Code:
  - Press `Ctrl+Shift+P` (Cmd+Shift+P on macOS) to open the Command Palette.
  - Type "Java: Configure Java Runtime" and select the JDK you installed (`/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home`).
  - Type "Java: Configure Java Project" and select the project folder.

6. **Develop Your Minecraft Mod:**

- Modify your `build.gradle` to include Minecraft Forge dependencies and configure tasks for building and running your mod.
- Use VS Code to edit Java files (`src/main/java`) and resources (`src/main/resources`) for your mod.

7. **Run and Debug Your Mod:**

- VS Code supports running and debugging Java applications via the Java Debugger extension included in the Java Extension Pack.
- Configure launch configurations (`launch.json`) for running your mod within Minecraft or testing it locally.

### Get Kotlin Version

```bash
./gradlew kotlin --version

------------------------------------------------------------
Gradle 8.8
------------------------------------------------------------

Build time:   2024-05-31 21:46:56 UTC
Revision:     4bd1b3d3fc3f31db5a26eecb416a165b8cc36082

Kotlin:       1.9.22
Groovy:       3.0.21
Ant:          Apache Ant(TM) version 1.10.13 compiled on January 4 2023
JVM:          22.0.1 (Oracle Corporation 22.0.1+8-16)
OS:           Mac OS X 14.5 aarch64
```

### Additional Notes

- **IDE Alternatives:** While IntelliJ IDEA is highly recommended for Java development due to its robust features and excellent Gradle support, VS Code is a lightweight and free alternative suitable for basic to moderate Java and Gradle projects.

- **Gradle Tasks:** You can run Gradle tasks (`build`, `runClient`, etc.) directly from VS Code's integrated terminal or using task configurations in `tasks.json`.

By following these steps, you can effectively use Visual Studio Code for developing your Minecraft mod with Gradle, leveraging its extensions and customization options tailored for Java development.
