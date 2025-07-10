import os

# ====== カスタマイズ部分 ======
crystal_colors = ["orange", "beige", "cyan", "purple", "black"]
package = "com.sakalti.sakaplus.ore"
modid = "sakaplus"
output_root = "./src/main/java"  # 出力ルートをここで明示指定
# ============================

java_package_path = os.path.join(output_root, *package.split('.'))

def ensure_dir(path):
    os.makedirs(path, exist_ok=True)

def write_file(filepath, content):
    with open(filepath, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"✓ 書き込み完了: {filepath}")

def generate_SakaPlusOreBlocks():
    lines = [
        f"package {package};\n",
        "import net.minecraft.block.*;",
        "import net.minecraft.sound.BlockSoundGroup;",
        "import net.minecraft.util.Identifier;",
        "import net.minecraft.util.registry.Registry;",
        "import net.minecraft.util.math.intprovider.UniformIntProvider;\n",
        "public class SakaPlusOreBlocks {"
    ]

    for color in crystal_colors:
        upper = color.upper()
        lines.append(f"    public static final Block {upper}_CRYSTAL_ORE = register(\"{color}_crystal_ore\");")

    lines.append("\n    private static Block register(String name) {")
    lines.append(f"        return Registry.register(Registry.BLOCK, new Identifier(\"{modid}\", name),")
    lines.append("            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE), UniformIntProvider.create(2, 5)));")
    lines.append("    }\n")

    lines.append("    public static void init() {")
    lines.append("        // 呼び出し用メソッド（空でもOK）")
    lines.append("    }")

    lines.append("}")
    return "\n".join(lines)

def generate_SakaPlusOreBlockItems():
    lines = [
        f"package {package};\n",
        "import net.minecraft.block.Block;",
        "import net.minecraft.item.*;",
        "import net.minecraft.util.Identifier;",
        "import net.minecraft.util.registry.Registry;\n",
        "public class SakaPlusOreBlockItems {",
        "    public static void init() {"
    ]

    for color in crystal_colors:
        upper = color.upper()
        lines.append(f"        registerBlockItem(\"{color}_crystal_ore\", SakaPlusOreBlocks.{upper}_CRYSTAL_ORE);")

    lines.append("    }\n")
    lines.append("    private static void registerBlockItem(String name, Block block) {")
    lines.append(f"        Registry.register(Registry.ITEM, new Identifier(\"{modid}\", name),")
    lines.append("            new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));")
    lines.append("    }")
    lines.append("}")
    return "\n".join(lines)

def main():
    ensure_dir(java_package_path)

    blocks_path = os.path.join(java_package_path, "SakaPlusOreBlocks.java")
    items_path = os.path.join(java_package_path, "SakaPlusOreBlockItems.java")

    write_file(blocks_path, generate_SakaPlusOreBlocks())
    write_file(items_path, generate_SakaPlusOreBlockItems())

if __name__ == "__main__":
    main()
