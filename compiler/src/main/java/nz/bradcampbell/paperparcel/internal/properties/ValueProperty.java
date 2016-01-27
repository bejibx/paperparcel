package nz.bradcampbell.paperparcel.internal.properties;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.ParameterSpec;
import nz.bradcampbell.paperparcel.internal.Property;
import org.jetbrains.annotations.Nullable;

import static nz.bradcampbell.paperparcel.internal.Utils.literal;

public class ValueProperty extends Property {
  public ValueProperty(Property.Type propertyType, boolean isNullable, String name) {
    // We can ignore isNullable here because readValue/writeValue handles null internally
    super(propertyType, false, name);
  }

  @Override protected CodeBlock readFromParcelInner(CodeBlock.Builder block, ParameterSpec in, @Nullable FieldSpec classLoader) {
    return literal("($T) $N.readValue($L)", getPropertyType().getTypeName(), in, classLoader);
  }

  @Override protected void writeToParcelInner(CodeBlock.Builder block, ParameterSpec dest, CodeBlock sourceLiteral) {
    block.addStatement("$N.writeValue($L)", dest, sourceLiteral);
  }
}