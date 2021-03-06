/*
 * Copyright (C) 2018. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.artist.api

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec

open class JavaViewStencil(
    extendedType: String,
    constructorCount: Int = 4,
    defaultAttrRes: String? = null,
    vararg addedTraits: Class<out JavaTrait> = emptyArray()
) : ViewStencil<TypeSpec.Builder, MethodSpec.Builder, ClassName, CodeBlock>(
    extendedType, constructorCount, defaultAttrRes, addedTraits.toSet()
) {

  val sourceType = ClassName.get(
      extendedType.substringBeforeLast('.'),
      extendedType.split('.').last()
  )

  /**
   * Hook for when attributes are being pulled out of the attribute set.
   * Can safely assume the following values exist:
   *   - context: Context
   *   - attrs: AttributeSet
   *   - defStyleAttr: int
   *   - a: TypedArray
   *
   * Should *not* recycle `a`. Safe to assume `a` is null-checked before code would execute.
   */
  override fun attrsHook(type: TypeSpec.Builder, initMethod: MethodSpec.Builder): CodeBlock? = null

  /**
   * Hook for implementing the `init()` method.
   */
  override fun initMethodHook(type: TypeSpec.Builder, initMethod: MethodSpec.Builder) {}

  /**
   * Hook for the type builder implementation.
   */
  override fun typeHook(type: TypeSpec.Builder) {}
}
