
import { Toaster } from "@/components/ui/toaster";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { TooltipProvider } from "@/components/ui/tooltip";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AppLayout } from "./components/AppLayout";
import Landing from "./pages/Landing";
import Partners from "./pages/Partners";
import Home from "./pages/Home";
import Search from "./pages/Search";
import Favorites from "./pages/Favorites";
import Profile from "./pages/Profile";
import Onboarding from "./pages/Onboarding";
import Marketplace from "./pages/Marketplace";
import OfferDetail from "./pages/OfferDetail";
import NotFound from "./pages/NotFound";

const queryClient = new QueryClient();

const App = () => (
  <QueryClientProvider client={queryClient}>
    <TooltipProvider>
      <Toaster />
      <Sonner />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Landing />} />
          <Route path="/partners" element={<Partners />} />
          <Route path="/onboarding" element={<Onboarding />} />
          <Route path="/marketplace" element={<Marketplace />} />
          <Route path="/marketplace/offer/:id" element={<OfferDetail />} />
          <Route path="/app" element={<AppLayout />}>
            <Route index element={<Home />} />
            <Route path="search" element={<Search />} />
            <Route path="favorites" element={<Favorites />} />
            <Route path="profile" element={<Profile />} />
          </Route>
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </TooltipProvider>
  </QueryClientProvider>
);

export default App;
