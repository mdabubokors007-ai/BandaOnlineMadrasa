-- v0.8: user activity support
create index if not exists favorites_user_idx on public.favorites(user_id);
create index if not exists recent_views_user_idx on public.recent_views(user_id);
create index if not exists download_history_user_idx on public.download_history(user_id);

alter table public.favorites enable row level security;
alter table public.recent_views enable row level security;
alter table public.download_history enable row level security;

drop policy if exists "users manage own favorites" on public.favorites;
create policy "users manage own favorites"
on public.favorites for all to authenticated
using (auth.uid() = user_id)
with check (auth.uid() = user_id);

drop policy if exists "users manage own recent views" on public.recent_views;
create policy "users manage own recent views"
on public.recent_views for all to authenticated
using (auth.uid() = user_id)
with check (auth.uid() = user_id);

drop policy if exists "users manage own download history" on public.download_history;
create policy "users manage own download history"
on public.download_history for all to authenticated
using (auth.uid() = user_id)
with check (auth.uid() = user_id);
