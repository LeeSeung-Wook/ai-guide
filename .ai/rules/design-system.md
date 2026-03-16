# Design System (Design Tokens)

본 프로젝트는 **Bootstrap 5.3**과 **Custom CSS Variables**를 결합하여 일관된 UI/UX를 유지합니다.

## 🎨 Color System (색상)
- `--color-primary`: `#197fe6` (메인 브랜드 컬러)
- `--color-secondary`: `#6c757d` (보조 컬러)
- `--color-success`: `#198754`
- `--color-danger`: `#dc3545`
- `--color-warning`: `#ffc107`
- `--color-info`: `#0dcaf0`
- `--color-light`: `#f8f9fa`
- `--color-dark`: `#212529`
- `--bg-soft`: `#f0f4f8` (배경 소프트 컬러)

## 🖋️ Typography (타이포그래피)
- `font-family`: `'Pretendard', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif`
- `--font-main`: `'Pretendard'`

## 📐 Shape & Spacing (모양 및 간격)
- `--border-radius-sm`: `8px`
- `--border-radius-md`: `12px`
- `--border-radius-lg`: `16px`
- `--spacing-xs`: `4px`
- `--spacing-sm`: `8px`
- `--spacing-md`: `16px`
- `--spacing-lg`: `24px`
- `--spacing-xl`: `32px`

## 🌑 Shadow (그림자)
- `--shadow-sm`: `0 2px 8px rgba(0, 0, 0, 0.05)`
- `--shadow-md`: `0 4px 16px rgba(0, 0, 0, 0.08)`
- `--shadow-lg`: `0 8px 24px rgba(0, 0, 0, 0.12)`

## 🧩 Components Guidelines
1. **Buttons**: Bootstrap 기본 클래스(`btn-primary` 등)에 `--border-radius-md`를 적용하여 부드러운 인상을 준다.
2. **Cards**: `card` 클래스에 `--shadow-sm`과 `--border-radius-md`를 조합하여 공중에 뜬 듯한 깔끔한 카드를 구성한다.
3. **Containers**: 페이지 전체를 감싸는 컨테이너는 Bootstrap `container` 클래스를 활용하며 상단 마진(`mt-5`)을 충분히 준다.
