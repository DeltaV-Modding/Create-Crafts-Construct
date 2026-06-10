# Create: Crafts & Construct

Create: Crafts & Construct is a NeoForge addon for Minecraft 1.21.1 built around Create. It adds new Create-adjacent blocks, items, foods, fluids, recipes, and an experimental paint system for recoloring selected Create blocks.

## Current Features

- Textile blocks and checkered textile variants in all vanilla dye colors.
- Jelly fluids, jelly buckets, and gummy bear food items.
- Sugar beet crop, seeds, item, recipes, loot, and tags.
- Fireball and dragon fireball charge items.
- Brass Item Drain variant.
- Paint Gun and Creative Paint Gun system.
- Temporary paint-variant blocks and PNG textures for testing material variants.

## Requirements

- Minecraft `1.21.1`
- NeoForge `21.1.139` or newer in the configured range
- Create `6.0.4-55`
- Java 21

Recommended local Java path on this machine:

```powershell
$env:JAVA_HOME='C:\Program Files\Amazon Corretto\jdk21.0.11_10'
```

## Development Setup

Clone the repository, open it in IntelliJ IDEA, and let Gradle import the project.

Useful commands:

```powershell
.\gradlew.bat compileJava
.\gradlew.bat build
.\gradlew.bat runClient
.\gradlew.bat runData
```

If dependencies are stale or missing:

```powershell
.\gradlew.bat --refresh-dependencies
```

Generated folders such as `build/`, `.gradle/`, `run/`, and IDE files are ignored and should not be committed.

## Paint System

The Paint Gun recolors supported Create blocks by replacing them with a matching material variant.

### Paint Gun

1. Put the `Paint Gun` in your main hand.
2. Put a paint cartridge in your off hand.
3. Right-click a supported block.
4. Each cartridge has 8 uses.
5. When a cartridge is empty, it becomes an `Empty Paint Cartridge`.

Available cartridges:

- `Andesite Paint Cartridge`
- `Brass Paint Cartridge`
- `Copper Paint Cartridge`
- `Train Paint Cartridge`

### Creative Paint Gun

The `Creative Paint Gun` does not consume cartridges. If a cartridge is in the off hand, it uses that cartridge's material. If no cartridge is present, it defaults to brass.

## Paintable Blocks

The paint system currently targets these groups:

- Create fluid blocks with implemented Crafts & Construct behavior: fluid pipes, smart fluid pipes, mechanical pumps, fluid valves, fluid tanks, spouts, hose pulleys, and portable fluid interfaces.
- Create kinetic and processing blocks with implemented Crafts & Construct behavior: gearboxes, encased chain drives, encased fans, millstones, mechanical saws, mechanical presses, mechanical mixers, deployers, mechanical drills, mechanical crafters, steam engines, and steam whistles.
- Create casing-style blocks such as copper, andesite, and brass casings.
- Create encased shafts and cogwheels.
- Create funnels and tunnels.
- Create kinetic machines such as fans, millstones, saws, presses, mixers, deployers, drills, and mechanical crafters.
- The modded `Brass Item Drain`.

Some targets are native Create variants. The Paint Gun only applies variants that keep correct behavior: native Create variants or explicitly implemented Crafts & Construct variants such as the Brass Item Drain and the implemented fluid blocks. Placeholder texture blocks may exist for artwork testing, but they are not used as functional Paint Gun targets.

## Temporary Paint Variant Textures

Temporary PNG textures are stored here:

```text
src/main/resources/assets/crafts_construct/textures/block/temp/
```

There are currently 80 temporary PNGs. They are intentionally simple 16x16 placeholder textures. Replace these files with final artwork later while keeping the same filenames to avoid changing block models.

Temporary block models are stored here:

```text
src/main/resources/assets/crafts_construct/models/block/
src/main/resources/assets/crafts_construct/models/item/
```

Temporary blockstates are stored here:

```text
src/main/resources/assets/crafts_construct/blockstates/
```

## Paint Variant Behavior

- Temporary texture blocks are available for artwork testing, but the Paint Gun avoids non-functional placeholders.
- The `Brass Item Drain` uses Create's item drain logic and registered item/fluid capabilities.
- Functional Crafts & Construct variants currently include fluid pipes, smart fluid pipes, mechanical pumps, fluid valves, fluid tanks, spouts, hose pulleys, portable fluid interfaces, gearboxes, encased chain drives, encased fans, millstones, mechanical saws, mechanical presses, mechanical mixers, deployers, mechanical drills, mechanical crafters, steam engines, steam whistles, and the Brass Item Drain.
- Other missing Create material variants are not used as functional Paint Gun targets until their behavior is implemented with proper block entity support.
- The paint system preserves shared blockstate properties where the source and target block both support the same property.
- Final artwork can be added later by replacing the PNGs in `src/main/resources/assets/crafts_construct/textures/block/temp/`.

## Project Structure

```text
src/main/java/net/buildercraft/        Main mod code, registries, items, blocks
src/main/java/util/                    Shared utility classes and paint helpers
src/main/resources/assets/             Models, textures, blockstates, lang
src/main/resources/data/               Tags, recipes, loot tables, data maps
src/generated/resources/               Generated resources from data generation
```

## Build Output

Build artifacts are generated under:

```text
build/libs/
```

Do not commit generated build outputs.

## License

This project is configured as MIT in `gradle.properties`.



